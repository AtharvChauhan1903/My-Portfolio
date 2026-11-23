const STORAGE_KEY = 'portfolio-expense-tracker-v1';
const elements = {
    status: document.querySelector('#tracker-status'),
    month: document.querySelector('#selected-month'),
    currency: document.querySelector('#currency-select'),
    budgetInput: document.querySelector('#budget-input'),
    saveBudget: document.querySelector('#save-budget'),
    resetMonth: document.querySelector('#reset-month'),
    saveRates: document.querySelector('#save-rates'),
    rates: {
        USD: document.querySelector('#rate-usd'),
        INR: document.querySelector('#rate-inr'),
        EUR: document.querySelector('#rate-eur'),
        GBP: document.querySelector('#rate-gbp')
    },
    spent: document.querySelector('#monthly-spent'),
    income: document.querySelector('#monthly-income'),
    remaining: document.querySelector('#budget-remaining'),
    remainingNote: document.querySelector('#remaining-note'),
    spentLabel: document.querySelector('#budget-spent-label'),
    totalLabel: document.querySelector('#budget-total-label'),
    progress: document.querySelector('#budget-progress'),
    progressFill: document.querySelector('#budget-progress-fill'),
    budgetMessage: document.querySelector('#budget-message'),
    form: document.querySelector('#transaction-form'),
    type: document.querySelector('#transaction-type'),
    description: document.querySelector('#transaction-description'),
    amount: document.querySelector('#transaction-amount'),
    transactionCurrency: document.querySelector('#transaction-currency'),
    category: document.querySelector('#transaction-category'),
    date: document.querySelector('#transaction-date'),
    filter: document.querySelector('#transaction-filter'),
    list: document.querySelector('#transaction-list')
};

const now = new Date();
const today = `${now.getFullYear()}-${String(now.getMonth() + 1).padStart(2, '0')}-${String(now.getDate()).padStart(2, '0')}`;
const currentMonth = today.slice(0, 7);
const DEFAULT_RATES = { USD: 1, INR: 83, EUR: 0.92, GBP: 0.79 };
let trackerData = {
    transactions: [],
    budgets: {},
    currency: 'INR',
    rates: { ...DEFAULT_RATES }
};
let storageReady = true;

elements.month.value = currentMonth;
elements.date.value = today;
elements.transactionCurrency.value = trackerData.currency;

function setStatus(message) {
    elements.status.textContent = message;
}

function validTrackerData(data) {
    return data &&
        Array.isArray(data.transactions) &&
        data.transactions.every((transaction) =>
            transaction &&
            typeof transaction.id === 'string' &&
            typeof transaction.type === 'string' &&
            ['expense', 'income'].includes(transaction.type) &&
            typeof transaction.description === 'string' &&
            typeof transaction.amount === 'number' &&
            Number.isFinite(transaction.amount) &&
            transaction.amount > 0 &&
            typeof transaction.category === 'string' &&
            typeof transaction.date === 'string' &&
            /^\d{4}-\d{2}-\d{2}$/.test(transaction.date) &&
            (transaction.currency === undefined ||
                ['INR', 'USD', 'EUR', 'GBP'].includes(transaction.currency))
        ) &&
        data.budgets &&
        typeof data.budgets === 'object' &&
        !Array.isArray(data.budgets) &&
        Object.values(data.budgets).every((budget) =>
            typeof budget === 'number' && Number.isFinite(budget) && budget > 0
        ) &&
        ['INR', 'USD', 'EUR', 'GBP'].includes(data.currency) &&
        (data.rates === undefined ||
            (typeof data.rates === 'object' &&
                ['INR', 'USD', 'EUR', 'GBP'].every((currency) =>
                    typeof data.rates[currency] === 'number' &&
                    Number.isFinite(data.rates[currency]) &&
                    data.rates[currency] > 0
                )));
}

function loadTrackerData() {
    try {
        const savedData = localStorage.getItem(STORAGE_KEY);
        if (savedData === null) {
            return;
        }

        const parsedData = JSON.parse(savedData);
        if (!validTrackerData(parsedData)) {
            throw new Error('Saved tracker data has an invalid format.');
        }

        trackerData = {
            ...parsedData,
            currency: parsedData.currency || 'INR',
            rates: parsedData.rates ? { ...DEFAULT_RATES, ...parsedData.rates } : { ...DEFAULT_RATES },
            transactions: parsedData.transactions.map((transaction) => ({
                ...transaction,
                currency: transaction.currency || parsedData.currency || 'INR'
            }))
        };
    } catch (error) {
        storageReady = false;
        setStatus(`Your saved tracker data could not be loaded: ${error.message}`);
    }
}

function saveTrackerData(updatedData) {
    try {
        localStorage.setItem(STORAGE_KEY, JSON.stringify(updatedData));
        trackerData = updatedData;
        setStatus('');
        renderTracker();
    } catch (error) {
        renderTracker();
        setStatus(`Your changes could not be saved: ${error.message}`);
    }
}

function formatMoney(value) {
    return new Intl.NumberFormat(undefined, {
        style: 'currency',
        currency: trackerData.currency,
        maximumFractionDigits: 2
    }).format(value);
}

function convertCurrency(amount, sourceCurrency) {
    const sourceRate = trackerData.rates[sourceCurrency] || trackerData.rates.USD;
    const targetRate = trackerData.rates[trackerData.currency];
    return (amount / sourceRate) * targetRate;
}

function transactionsForMonth() {
    const month = elements.month.value;
    return trackerData.transactions.filter((transaction) => transaction.date.startsWith(month));
}

function renderSummary() {
    const monthTransactions = transactionsForMonth();
    const spent = monthTransactions
        .filter((transaction) => transaction.type === 'expense')
        .reduce((total, transaction) =>
            total + convertCurrency(transaction.amount, transaction.currency), 0);
    const income = monthTransactions
        .filter((transaction) => transaction.type === 'income')
        .reduce((total, transaction) =>
            total + convertCurrency(transaction.amount, transaction.currency), 0);
    const budget = trackerData.budgets[elements.month.value] || 0;
    const remaining = budget - spent;
    const percentage = budget > 0 ? Math.min((spent / budget) * 100, 100) : 0;
    const overBudget = budget > 0 && spent > budget;

    elements.spent.textContent = formatMoney(spent);
    elements.income.textContent = formatMoney(income);
    elements.remaining.textContent = formatMoney(remaining);
    elements.remaining.classList.toggle('is-over-budget', overBudget);
    elements.remainingNote.textContent = overBudget
        ? `${formatMoney(Math.abs(remaining))} over your budget`
        : budget > 0 ? 'Left to spend this month' : 'Set a monthly budget to get started';
    elements.budgetInput.value = budget || '';
    elements.spentLabel.textContent = `${formatMoney(spent)} spent`;
    elements.totalLabel.textContent = `of ${formatMoney(budget)}`;
    elements.progress.setAttribute('aria-valuenow', String(Math.round(percentage)));
    elements.progress.setAttribute('aria-valuetext', budget > 0
        ? `${Math.round((spent / budget) * 100)} percent of budget used`
        : 'No budget set');
    elements.progress.classList.toggle('is-over-budget', overBudget);
    elements.progressFill.style.width = `${percentage}%`;
    elements.budgetMessage.textContent = !budget
        ? 'Add a budget above to see your progress.'
        : overBudget
            ? `You’re ${formatMoney(Math.abs(remaining))} over your budget.`
            : `${Math.round(percentage)}% of your monthly budget used.`;
}

function formatDate(value) {
    const [year, month, day] = value.split('-').map(Number);
    return new Date(year, month - 1, day).toLocaleDateString(undefined, {
        month: 'short',
        day: 'numeric',
        year: 'numeric'
    });
}

function renderTransactions() {
    const selectedType = elements.filter.value;
    const visibleTransactions = transactionsForMonth()
        .filter((transaction) => selectedType === 'all' || transaction.type === selectedType)
        .sort((first, second) => second.date.localeCompare(first.date));

    elements.list.replaceChildren();

    if (visibleTransactions.length === 0) {
        const emptyMessage = document.createElement('li');
        emptyMessage.className = 'empty-state';
        emptyMessage.textContent = selectedType === 'all'
            ? 'Nothing recorded for this month yet. Add your first transaction to get started.'
            : `No ${selectedType === 'expense' ? 'expenses' : 'income'} recorded for this month.`;
        elements.list.append(emptyMessage);
        return;
    }

    for (const transaction of visibleTransactions) {
        const item = document.createElement('li');
        item.className = 'transaction-item';

        const details = document.createElement('div');
        details.className = 'transaction-details';

        const title = document.createElement('span');
        title.className = 'transaction-title';
        title.textContent = transaction.description;

        const meta = document.createElement('span');
        meta.className = 'transaction-meta';
        meta.textContent = `${transaction.category} · ${formatDate(transaction.date)}`;
        details.append(title, meta);

        const actions = document.createElement('div');
        actions.className = 'transaction-actions';

        const amount = document.createElement('span');
        amount.className = `transaction-amount${transaction.type === 'income' ? ' is-income' : ''}`;
        const originalAmount = new Intl.NumberFormat(undefined, {
            style: 'currency',
            currency: transaction.currency,
            maximumFractionDigits: 2
        }).format(transaction.amount);
        const convertedAmount = convertCurrency(transaction.amount, transaction.currency);
        amount.textContent = `${transaction.type === 'income' ? '+' : '−'}${originalAmount}${transaction.currency !== trackerData.currency
            ? ` (${formatMoney(convertedAmount)})`
            : ''}`;

        const deleteButton = document.createElement('button');
        deleteButton.className = 'delete-transaction';
        deleteButton.type = 'button';
        deleteButton.textContent = 'Remove';
        deleteButton.setAttribute('aria-label', `Remove ${transaction.type}: ${transaction.description}`);
        deleteButton.addEventListener('click', () => {
            saveTrackerData({
                ...trackerData,
                transactions: trackerData.transactions.filter((saved) => saved.id !== transaction.id)
            });
        });

        actions.append(amount, deleteButton);
        item.append(details, actions);
        elements.list.append(item);
    }
}

function renderTracker() {
    elements.currency.value = trackerData.currency;
    elements.transactionCurrency.value = trackerData.currency;
    for (const [currency, input] of Object.entries(elements.rates)) {
        input.value = trackerData.rates[currency];
    }
    renderSummary();
    renderTransactions();
}

elements.saveBudget.addEventListener('click', () => {
    if (!storageReady) {
        return;
    }

    const budget = Number(elements.budgetInput.value);
    if (!Number.isFinite(budget) || budget <= 0) {
        setStatus('Enter a budget greater than zero.');
        elements.budgetInput.focus();
        return;
    }

    saveTrackerData({
        ...trackerData,
        budgets: { ...trackerData.budgets, [elements.month.value]: budget }
    });
});

elements.saveRates.addEventListener('click', () => {
    if (!storageReady) {
        return;
    }

    const rates = {};
    for (const [currency, input] of Object.entries(elements.rates)) {
        const rate = Number(input.value);
        if (!Number.isFinite(rate) || rate <= 0) {
            setStatus(`Enter a valid exchange rate for ${currency}.`);
            input.focus();
            return;
        }
        rates[currency] = rate;
    }

    saveTrackerData({ ...trackerData, rates });
    setStatus('Exchange rates saved.');
});

elements.resetMonth.addEventListener('click', () => {
    if (!storageReady) {
        return;
    }

    const selectedMonth = elements.month.value;
    const [year, month] = selectedMonth.split('-').map(Number);
    const monthName = new Date(year, month - 1, 1).toLocaleDateString(undefined, {
        month: 'long',
        year: 'numeric'
    });
    const confirmed = globalThis.confirm(
        `Reset ${monthName}? This will remove this month's transactions and budget.`
    );
    if (!confirmed) {
        return;
    }

    const budgets = { ...trackerData.budgets };
    delete budgets[selectedMonth];
    saveTrackerData({
        ...trackerData,
        budgets,
        transactions: trackerData.transactions.filter(
            (transaction) => !transaction.date.startsWith(selectedMonth)
        )
    });
    setStatus(`${monthName} has been reset.`);
});

elements.type.addEventListener('change', () => {
    if (elements.type.value === 'income' && elements.category.value !== 'Salary') {
        elements.category.value = 'Salary';
    } else if (elements.type.value === 'expense' && elements.category.value === 'Salary') {
        elements.category.value = 'Food';
    }
});

elements.form.addEventListener('submit', (event) => {
    event.preventDefault();
    if (!storageReady) {
        return;
    }

    const description = elements.description.value.trim();
    const amount = Number(elements.amount.value);
    if (!description || !Number.isFinite(amount) || amount <= 0 || !elements.date.value) {
        setStatus('Add a description, a valid amount, and a date.');
        return;
    }

    const transaction = {
        id: globalThis.crypto.randomUUID(),
        type: elements.type.value,
        description,
        amount,
        currency: elements.transactionCurrency.value,
        category: elements.category.value,
        date: elements.date.value
    };

    saveTrackerData({
        ...trackerData,
        transactions: [...trackerData.transactions, transaction]
    });
    if (trackerData.transactions.some((saved) => saved.id === transaction.id)) {
        elements.form.reset();
        elements.date.value = today;
        elements.transactionCurrency.value = trackerData.currency;
        elements.description.focus();
    }
});

elements.currency.addEventListener('change', () => {
    if (!storageReady) {
        return;
    }

    saveTrackerData({ ...trackerData, currency: elements.currency.value });
});

elements.month.addEventListener('change', () => {
    if (!elements.month.value) {
        elements.month.value = currentMonth;
    }
    renderTracker();
});
elements.filter.addEventListener('change', renderTransactions);

loadTrackerData();
renderTracker();
if (!storageReady) {
    elements.form.querySelector('button[type="submit"]').disabled = true;
    elements.saveBudget.disabled = true;
    elements.resetMonth.disabled = true;
    elements.saveRates.disabled = true;
    elements.currency.disabled = true;
}
