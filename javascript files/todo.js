const STORAGE_KEY = 'portfolio-todo-tasks';
const taskForm = document.querySelector('#task-form');
const taskInput = document.querySelector('#task-input');
const dateInput = document.querySelector('#task-date');
const taskList = document.querySelector('#task-list');
const statusMessage = document.querySelector('#status-message');

let tasks = [];
let storageReady = true;

function setStatus(message, isError = false) {
    statusMessage.textContent = message;
    statusMessage.classList.toggle('is-error', isError);
}

function loadTasks() {
    try {
        const savedTasks = localStorage.getItem(STORAGE_KEY);
        if (savedTasks === null) {
            return;
        }

        const parsedTasks = JSON.parse(savedTasks);
        if (!Array.isArray(parsedTasks) || parsedTasks.some((task) =>
            !task ||
            typeof task.id !== 'string' ||
            typeof task.text !== 'string' ||
            typeof task.dueDate !== 'string'
        )) {
            throw new Error('Saved task data has an invalid format.');
        }

        tasks = parsedTasks;
    } catch (error) {
        storageReady = false;
        setStatus(`Saved tasks could not be loaded: ${error.message}`, true);
    }
}

function saveTasks(updatedTasks) {
    try {
        localStorage.setItem(STORAGE_KEY, JSON.stringify(updatedTasks));
        tasks = updatedTasks;
        setStatus('');
        renderTasks();
    } catch (error) {
        setStatus(`Tasks could not be saved: ${error.message}`, true);
    }
}

function formatDueDate(value) {
    if (!value) {
        return '';
    }

    const [year, month, day] = value.split('-').map(Number);
    return new Date(year, month - 1, day).toLocaleDateString(undefined, {
        year: 'numeric',
        month: 'short',
        day: 'numeric'
    });
}

function renderTasks() {
    taskList.replaceChildren();

    if (tasks.length === 0) {
        const emptyMessage = document.createElement('li');
        emptyMessage.className = 'empty-state';
        emptyMessage.textContent = 'No tasks yet. Add one above to get started.';
        taskList.append(emptyMessage);
        return;
    }

    for (const task of tasks) {
        const item = document.createElement('li');
        item.className = 'task-item';

        const details = document.createElement('div');
        details.className = 'task-details';

        const text = document.createElement('span');
        text.className = 'task-text';
        text.textContent = task.text;
        details.append(text);

        if (task.dueDate) {
            const dueDate = document.createElement('time');
            dueDate.className = 'task-date';
            dueDate.dateTime = task.dueDate;
            dueDate.textContent = `Due ${formatDueDate(task.dueDate)}`;
            details.append(dueDate);
        }

        const removeButton = document.createElement('button');
        removeButton.className = 'remove-button';
        removeButton.type = 'button';
        removeButton.textContent = 'Remove';
        removeButton.setAttribute('aria-label', `Remove task: ${task.text}`);
        removeButton.addEventListener('click', () => {
            saveTasks(tasks.filter((savedTask) => savedTask.id !== task.id));
        });

        item.append(details, removeButton);
        taskList.append(item);
    }
}

taskForm.addEventListener('submit', (event) => {
    event.preventDefault();

    if (!storageReady) {
        return;
    }

    const text = taskInput.value.trim();
    if (!text) {
        taskInput.focus();
        return;
    }

    const updatedTasks = [
        ...tasks,
        {
            id: globalThis.crypto.randomUUID(),
            text,
            dueDate: dateInput.value
        }
    ];

    saveTasks(updatedTasks);
    if (tasks.length === updatedTasks.length) {
        taskForm.reset();
        taskInput.focus();
    }
});

loadTasks();
renderTasks();
if (!storageReady) {
    taskForm.querySelector('button[type="submit"]').disabled = true;
}
