const display = document.querySelector("#display");
const message = document.querySelector("#calculator-message");
const keypad = document.querySelector("#keypad");
const expressionCalculator = document.querySelector("#expression-calculator");
const dateCalculator = document.querySelector("#date-calculator");
const programmerOptions = document.querySelector("#programmer-options");
const baseResults = document.querySelector("#base-results");
const angleControl = document.querySelector("#angle-control");
const angleUnit = document.querySelector("#angle-unit");
let currentMode = "standard";
let justCalculated = false;

function showMessage(text = "Use your keyboard or the buttons.") {
    message.textContent = text;
}

function getNumberBase() {
    return Number(document.querySelector('input[name="number-base"]:checked').value);
}

function setMode(mode) {
    currentMode = mode;
    document.querySelectorAll("[data-mode]").forEach((button) => {
        button.setAttribute("aria-pressed", String(button.dataset.mode === mode));
    });
    const isDate = mode === "date";
    expressionCalculator.hidden = isDate;
    dateCalculator.hidden = !isDate;
    programmerOptions.hidden = mode !== "programmer";
    angleControl.hidden = mode !== "scientific";
    document.querySelectorAll(".scientific-key").forEach((button) => {
        button.hidden = mode !== "scientific";
    });
    document.querySelectorAll(".programmer-key").forEach((button) => {
        button.hidden = mode !== "programmer";
    });
    document.querySelectorAll(".standard-key").forEach((button) => {
        button.hidden = mode === "programmer" && !button.classList.contains("programmer-key");
    });
    display.placeholder = mode === "programmer" ? "Enter an integer expression" : "0";
    updateProgrammerKeypad();
    showMessage(
        mode === "programmer"
            ? "32-bit integer mode. Choose a base and use bitwise operators."
            : mode === "scientific"
                ? "Scientific mode. Trigonometry uses the selected angle unit."
                : "Use your keyboard or the buttons."
    );
}

function updateProgrammerKeypad() {
    if (currentMode !== "programmer") return;
    const radix = getNumberBase();
    document.querySelectorAll(".digit-key, .programmer-key[data-digit]").forEach((button) => {
        const digit = button.dataset.digit;
        const digitValue = Number.parseInt(digit, 16);
        button.hidden = digitValue >= radix;
    });
}

function appendValue(value) {
    if (
        justCalculated &&
        (/^[0-9.(πeA-Fa-f]$/.test(value) ||
            /^[a-z]+\($/i.test(value) ||
            (currentMode === "programmer" && value === "~"))
    ) {
        display.value = "";
    }
    justCalculated = false;
    display.value += value;
    showMessage();
    display.focus();
}

function clearDisplay() {
    display.value = "";
    baseResults.textContent = "";
    justCalculated = false;
    showMessage("Cleared.");
    display.focus();
}

function deleteLastCharacter() {
    display.value = display.value.slice(0, -1);
    baseResults.textContent = "";
    justCalculated = false;
    showMessage();
    display.focus();
}

function toggleSign() {
    const expression = display.value.trim();
    if (!expression) {
        display.value = "-";
    } else if (/^-?\d*\.?\d+$/.test(expression)) {
        display.value = String(-Number(expression));
    } else {
        display.value = `-(${expression})`;
    }
    justCalculated = false;
    showMessage();
    display.focus();
    display.setSelectionRange(display.value.length, display.value.length);
}

function evaluateExpression(source) {
    const input = source.replaceAll("×", "*").replaceAll("÷", "/").replaceAll("−", "-").replaceAll("π", "pi");
    let position = 0;

    function skipSpaces() {
        while (/\s/.test(input[position] || "")) position += 1;
    }

    function parseExpression() {
        let value = parseTerm();
        skipSpaces();
        while (input[position] === "+" || input[position] === "-") {
            const operator = input[position++];
            const right = parseTerm();
            value = operator === "+" ? value + right : value - right;
            skipSpaces();
        }
        return value;
    }

    function parseTerm() {
        let value = parseUnary();
        skipSpaces();
        while (input[position] === "*" || input[position] === "/") {
            const operator = input[position++];
            const right = parseUnary();
            if (operator === "/" && right === 0) throw new Error("Cannot divide by zero.");
            value = operator === "*" ? value * right : value / right;
            skipSpaces();
        }
        return value;
    }

    function parseUnary() {
        skipSpaces();
        if (input[position] === "+" || input[position] === "-") {
            const operator = input[position++];
            const value = parseUnary();
            return operator === "-" ? -value : value;
        }
        return parsePower();
    }

    function parsePower() {
        let value = parsePostfix();
        skipSpaces();
        if (input[position] === "^") {
            position += 1;
            value **= parseUnary();
        }
        return value;
    }

    function parsePostfix() {
        let value = parsePrimary();
        skipSpaces();
        while (input[position] === "!" || input[position] === "%") {
            const operator = input[position++];
            if (operator === "%") {
                value /= 100;
            } else {
                if (!Number.isInteger(value) || value < 0 || value > 170) {
                    throw new Error("Factorial requires a whole number from 0 to 170.");
                }
                let result = 1;
                for (let factor = 2; factor <= value; factor += 1) result *= factor;
                value = result;
            }
            skipSpaces();
        }
        return value;
    }

    function parsePrimary() {
        skipSpaces();
        if (input[position] === "(") {
            position += 1;
            const value = parseExpression();
            skipSpaces();
            if (input[position] !== ")") throw new Error("Check the expression.");
            position += 1;
            return value;
        }

        const nameStart = position;
        while (/[a-z]/i.test(input[position] || "")) position += 1;
        if (position > nameStart) {
            const name = input.slice(nameStart, position).toLowerCase();
            if (name === "pi") return Math.PI;
            if (name === "e") return Math.E;
            skipSpaces();
            if (input[position] !== "(") throw new Error("Check the expression.");
            position += 1;
            const argument = parseExpression();
            skipSpaces();
            if (input[position] !== ")") throw new Error("Check the expression.");
            position += 1;
            return evaluateFunction(name, argument);
        }

        const start = position;
        let digits = 0;
        while (/[0-9]/.test(input[position] || "")) {
            position += 1;
            digits += 1;
        }
        if (input[position] === ".") {
            position += 1;
            while (/[0-9]/.test(input[position] || "")) {
                position += 1;
                digits += 1;
            }
        }
        if (digits === 0) throw new Error("Check the expression.");
        if (input[position] === "e" || input[position] === "E") {
            position += 1;
            if (input[position] === "+" || input[position] === "-") position += 1;
            const exponentStart = position;
            while (/[0-9]/.test(input[position] || "")) position += 1;
            if (position === exponentStart) throw new Error("Check the expression.");
        }
        return Number(input.slice(start, position));
    }

    if (!input.trim()) throw new Error("Enter an expression.");
    const result = parseExpression();
    skipSpaces();
    if (position !== input.length || !Number.isFinite(result)) throw new Error("Check the expression.");
    return Number.parseFloat(result.toPrecision(12)).toString();
}

function evaluateFunction(name, value) {
    const angle = angleUnit.value === "deg" ? Math.PI / 180 : 1;
    const inverseAngle = angleUnit.value === "deg" ? 180 / Math.PI : 1;
    const functions = {
        sin: () => Math.sin(value * angle),
        cos: () => Math.cos(value * angle),
        tan: () => Math.tan(value * angle),
        asin: () => Math.asin(value) * inverseAngle,
        acos: () => Math.acos(value) * inverseAngle,
        atan: () => Math.atan(value) * inverseAngle,
        sqrt: () => Math.sqrt(value),
        cbrt: () => Math.cbrt(value),
        ln: () => Math.log(value),
        log: () => Math.log10(value),
        abs: () => Math.abs(value),
        exp: () => Math.exp(value),
        floor: () => Math.floor(value),
        ceil: () => Math.ceil(value),
        round: () => Math.round(value)
    };
    if (!functions[name]) throw new Error(`Unknown function: ${name}`);
    const result = functions[name]();
    if (!Number.isFinite(result)) throw new Error("That operation has no real result.");
    return result;
}

function evaluateProgrammerExpression(source, radix) {
    const tokens = [];
    let index = 0;
    while (index < source.length) {
        const character = source[index];
        if (/\s/.test(character)) {
            index += 1;
            continue;
        }
        if (/[0-9A-Fa-f]/.test(character)) {
            const start = index;
            while (/[0-9A-Fa-f]/.test(source[index] || "")) index += 1;
            const digits = source.slice(start, index);
            if ([...digits].some((digit) => Number.parseInt(digit, 16) >= radix)) {
                throw new Error(`Use only base-${radix} digits.`);
            }
            const value = Number.parseInt(digits, radix);
            if (!Number.isSafeInteger(value) || value > 0xffffffff) {
                throw new Error("Values must fit in 32 bits.");
            }
            tokens.push({ type: "number", value });
            continue;
        }
        const operator = ["<<", ">>"].find((candidate) => source.startsWith(candidate, index)) || character;
        if ("()+-*/%&|^~".includes(operator) || operator === "<<" || operator === ">>") {
            tokens.push({ type: "operator", value: operator });
            index += operator.length;
            continue;
        }
        throw new Error("Use integers and programmer operators only.");
    }
    if (tokens.length === 0) throw new Error("Enter an integer expression.");

    const output = [];
    const operators = [];
    const precedence = { "|": 1, "^": 2, "&": 3, "<<": 4, ">>": 4, "+": 5, "-": 5, "*": 6, "/": 6, "%": 6, "u+": 7, "u-": 7, "~": 7 };
    let expectsValue = true;
    for (const token of tokens) {
        if (token.type === "number") {
            if (!expectsValue) throw new Error("Check the expression.");
            output.push(token);
            expectsValue = false;
            continue;
        }
        const operator = token.value;
        if (operator === "(") {
            if (!expectsValue) throw new Error("Check the expression.");
            operators.push(operator);
            expectsValue = true;
            continue;
        }
        if (operator === ")") {
            if (expectsValue) throw new Error("Check the expression.");
            while (operators.length && operators.at(-1) !== "(") output.push({ type: "operator", value: operators.pop() });
            if (operators.pop() !== "(") throw new Error("Check the expression.");
            expectsValue = false;
            continue;
        }
        let current = operator;
        if (expectsValue && (operator === "+" || operator === "-" || operator === "~")) {
            current = operator === "+" ? "u+" : operator === "-" ? "u-" : "~";
        } else if (expectsValue) {
            throw new Error("Check the expression.");
        }
        while (
            operators.length &&
            operators.at(-1) !== "(" &&
            (precedence[operators.at(-1)] > precedence[current] ||
                (precedence[operators.at(-1)] === precedence[current] && !current.startsWith("u")))
        ) {
            output.push({ type: "operator", value: operators.pop() });
        }
        operators.push(current);
        expectsValue = true;
    }
    if (expectsValue) throw new Error("Check the expression.");
    while (operators.length) {
        const operator = operators.pop();
        if (operator === "(") throw new Error("Check the expression.");
        output.push({ type: "operator", value: operator });
    }

    const values = [];
    for (const token of output) {
        if (token.type === "number") {
            values.push(token.value);
            continue;
        }
        const operator = token.value;
        if (["u+", "u-", "~"].includes(operator)) {
            if (!values.length) throw new Error("Check the expression.");
            const value = values.pop();
            values.push(operator === "u-" ? -value | 0 : operator === "~" ? ~value : value);
            continue;
        }
        if (values.length < 2) throw new Error("Check the expression.");
        const right = values.pop();
        const left = values.pop();
        if ((operator === "/" || operator === "%") && right === 0) throw new Error("Cannot divide by zero.");
        if ((operator === "<<" || operator === ">>") && (!Number.isInteger(right) || right < 0 || right > 31)) {
            throw new Error("Shift amount must be from 0 to 31.");
        }
        const result = {
            "+": () => left + right,
            "-": () => left - right,
            "*": () => left * right,
            "/": () => Math.trunc(left / right),
            "%": () => left % right,
            "&": () => left & right,
            "|": () => left | right,
            "^": () => left ^ right,
            "<<": () => left << right,
            ">>": () => left >> right
        }[operator]();
        values.push(result | 0);
    }
    if (values.length !== 1) throw new Error("Check the expression.");
    return values[0] | 0;
}

function displayProgrammerConversions(result) {
    const unsigned = result >>> 0;
    baseResults.textContent = `HEX: ${unsigned.toString(16).toUpperCase()}  |  DEC: ${result}  |  OCT: ${unsigned.toString(8)}  |  BIN: ${unsigned.toString(2)}`;
}

function calculate() {
    try {
        if (currentMode === "programmer") {
            const result = evaluateProgrammerExpression(display.value, getNumberBase());
            display.value = result.toString(getNumberBase()).toUpperCase();
            displayProgrammerConversions(result);
        } else {
            display.value = evaluateExpression(display.value);
        }
        justCalculated = true;
        showMessage("Calculated.");
    } catch (error) {
        showMessage(error.message);
    }
    display.focus();
    display.setSelectionRange(display.value.length, display.value.length);
}

function handleAction(action) {
    if (action === "clear") clearDisplay();
    if (action === "backspace") deleteLastCharacter();
    if (action === "calculate") calculate();
    if (action === "negate" && currentMode !== "programmer") toggleSign();
}

function parseUtcDate(value) {
    if (!/^\d{4}-\d{2}-\d{2}$/.test(value)) return null;
    const [year, month, day] = value.split("-").map(Number);
    if (year < 1 || year > 9999) return null;
    const date = new Date(0);
    date.setUTCFullYear(year, month - 1, day);
    date.setUTCHours(0, 0, 0, 0);
    if (date.getUTCFullYear() !== year || date.getUTCMonth() !== month - 1 || date.getUTCDate() !== day) return null;
    return date;
}

function formatUtcDate(date) {
    return date.toISOString().slice(0, 10);
}

function calculateDateDifference() {
    const start = parseUtcDate(document.querySelector("#date-start").value);
    const end = parseUtcDate(document.querySelector("#date-end").value);
    if (!start || !end) {
        document.querySelector("#date-result").textContent = "Choose two valid dates.";
        return;
    }
    const days = Math.abs((end - start) / 86400000);
    const direction = days === 0 ? "apart" : end > start ? "later" : "earlier";
    document.querySelector("#date-result").textContent =
        `${days} day${days === 1 ? "" : "s"} (${(days / 7).toFixed(2)} weeks) ${direction}.`;
}

function addDateAmount(unit, direction) {
    const dateInput = document.querySelector("#date-base");
    const date = parseUtcDate(dateInput.value);
    const amountInput = document.querySelector("#date-amount");
    const amount = Number(amountInput.value);
    if (!date || !Number.isSafeInteger(amount) || amount < 0) {
        document.querySelector("#date-result").textContent = "Choose a valid date and a non-negative whole number.";
        return;
    }
    const signedAmount = amount * direction;
    if (unit === "days" || unit === "weeks") {
        date.setUTCDate(date.getUTCDate() + signedAmount * (unit === "weeks" ? 7 : 1));
    } else {
        const originalDay = date.getUTCDate();
        const monthOffset = signedAmount * (unit === "years" ? 12 : 1);
        const targetMonth = new Date(0);
        targetMonth.setUTCFullYear(date.getUTCFullYear(), date.getUTCMonth() + monthOffset, 1);
        targetMonth.setUTCHours(0, 0, 0, 0);
        const monthEnd = new Date(0);
        monthEnd.setUTCFullYear(targetMonth.getUTCFullYear(), targetMonth.getUTCMonth() + 1, 0);
        monthEnd.setUTCHours(0, 0, 0, 0);
        const finalDay = monthEnd.getUTCDate();
        targetMonth.setUTCDate(Math.min(originalDay, finalDay));
        date.setTime(targetMonth.getTime());
    }
    if (!Number.isFinite(date.getTime()) || date.getUTCFullYear() < 1 || date.getUTCFullYear() > 9999) {
        document.querySelector("#date-result").textContent = "The result is outside the supported date range.";
        return;
    }
    document.querySelector("#date-result").textContent = `Result: ${formatUtcDate(date)}`;
}

keypad.addEventListener("click", (event) => {
    const button = event.target.closest("button");
    if (!button || button.hidden) return;
    if (button.dataset.action) {
        handleAction(button.dataset.action);
    } else if (button.dataset.digit) {
        appendValue(button.dataset.digit);
    } else if (button.dataset.value) {
        appendValue(button.dataset.value);
    }
});

document.querySelectorAll("[data-mode]").forEach((button) => {
    button.addEventListener("click", () => setMode(button.dataset.mode));
});

document.querySelectorAll('input[name="number-base"]').forEach((radio) => {
    radio.addEventListener("change", () => {
        updateProgrammerKeypad();
        baseResults.textContent = "";
    });
});

display.addEventListener("input", () => {
    justCalculated = false;
    baseResults.textContent = "";
    showMessage();
});

document.querySelectorAll("[data-date-mode]").forEach((button) => {
    button.addEventListener("click", () => {
        const differenceMode = button.dataset.dateMode === "difference";
        document.querySelectorAll("[data-date-mode]").forEach((modeButton) => {
            modeButton.setAttribute("aria-pressed", String(modeButton === button));
        });
        document.querySelector("#date-difference").hidden = !differenceMode;
        document.querySelector("#date-addition").hidden = differenceMode;
        document.querySelector("#date-result").textContent = "Choose dates to calculate.";
    });
});

document.querySelectorAll("[data-date-action]").forEach((button) => {
    button.addEventListener("click", () => {
        const action = button.dataset.dateAction;
        if (action === "difference") {
            calculateDateDifference();
        } else {
            addDateAmount(
                document.querySelector("#date-unit").value,
                action === "add" ? 1 : -1
            );
        }
    });
});

document.addEventListener("keydown", (event) => {
    if (event.key === "Escape") {
        event.preventDefault();
        clearDisplay();
        return;
    }
    if (currentMode === "date") return;
    if (
        document.activeElement === display &&
        justCalculated &&
        (currentMode === "programmer" ? /^[0-9A-Fa-f(]$/ : /^[0-9.(A-Za-z]$/).test(event.key)
    ) {
        display.value = "";
        justCalculated = false;
        return;
    }
    if (event.key === "Enter" || event.key === "=") {
        event.preventDefault();
        calculate();
        return;
    }
    if (event.key === "Backspace" && document.activeElement !== display) {
        event.preventDefault();
        deleteLastCharacter();
        return;
    }
    if (document.activeElement === display) {
        if (currentMode === "programmer" && !/^[0-9A-Fa-f\s()+\-*/%&|^~<>]$/.test(event.key) && event.key.length === 1) {
            event.preventDefault();
        }
        return;
    }
    const allowed = currentMode === "programmer" ? /^[0-9A-Fa-f()+\-*/%&|^~<>]$/ : /^[0-9.+*/()\-^%!A-Za-z]$/;
    if (allowed.test(event.key)) {
        event.preventDefault();
        appendValue(event.key);
    }
});

const today = formatUtcDate(new Date());
document.querySelector("#date-start").value = today;
document.querySelector("#date-end").value = today;
document.querySelector("#date-base").value = today;
document.querySelector("#angle-unit").addEventListener("change", () => showMessage("Angle unit updated."));
setMode("standard");
