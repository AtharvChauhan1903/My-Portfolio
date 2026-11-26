const typingLevels = {
    beginner: {
        name: 'Beginner',
        benchmark: 20,
        sentences: [
            'A calm start can make a new task feel much easier.',
            'I learn a little more each time I practice.',
            'Take a slow breath and focus on the next word.',
            'Small steps can help us reach a big goal.',
            'A clear plan makes it easier to get started.',
            'There is always time to try again and improve.',
            'Good work grows when we stay patient and curious.',
            'One careful choice can lead to another.',
            'A short break can help us feel ready to continue.',
            'Keep moving forward, even when progress feels small.',
            'Today is a good day to learn something new.',
            'Every page begins with a single word.'
        ]
    },
    experienced: {
        name: 'Experienced',
        benchmark: 40,
        sentences: [
            'Good habits turn a difficult task into a series of manageable steps.',
            'A thoughtful plan leaves enough room to adapt when priorities change.',
            'People learn faster when they can practice, reflect, and try again.',
            'Clear instructions help a team make steady progress together.',
            'A useful routine balances focused work with time to recharge.',
            'Reliable results often come from small improvements repeated each day.',
            'Paying attention to details can prevent avoidable mistakes later.',
            'The best solution is usually the one people can use with confidence.',
            'Regular practice builds both skill and a better sense of timing.',
            'Good communication makes complex work easier to coordinate.',
            'A fresh perspective can reveal options that were easy to overlook.',
            'Taking notes helps turn new ideas into practical next steps.'
        ]
    },
    professional: {
        name: 'Professional',
        benchmark: 60,
        sentences: [
            'Effective collaboration depends on clear expectations, consistent feedback, and shared accountability.',
            'A well-designed process reduces unnecessary friction without limiting thoughtful decision-making.',
            'Teams make stronger progress when they identify risks before those risks disrupt delivery.',
            'Careful prioritization helps professionals protect quality while meeting demanding deadlines.',
            'The most useful insights emerge when evidence is considered alongside practical experience.',
            'Reliable systems make routine work predictable and unusual problems easier to investigate.',
            'A concise explanation can make a complicated recommendation easier to evaluate.',
            'Successful projects require both a compelling direction and attention to implementation details.',
            'Good documentation preserves context and helps others contribute with fewer delays.',
            'Measurable goals make it easier to recognize progress and adjust a strategy.',
            'Thoughtful review turns individual observations into improvements the whole team can use.',
            'Responsible planning accounts for changing requirements, limited resources, and long-term maintenance.'
        ]
    },
    competitive: {
        name: 'Competitive',
        benchmark: 80,
        sentences: [
            'Precision under pressure depends on disciplined practice, deliberate pacing, and consistent execution.',
            'A competitive typist balances rapid recognition with reliable control of punctuation and spacing.',
            'Complex material rewards anticipation: identify the structure, maintain cadence, and avoid needless corrections.',
            'Fluency is not simply speed; it is accurate movement through increasingly demanding sequences.',
            'Experienced competitors refine technique through measurement, repetition, and careful analysis of errors.',
            'Dense passages challenge concentration with unfamiliar vocabulary, varied clauses, and shifting emphasis.',
            'Sustainable performance combines ergonomic habits with focused preparation and measured recovery.',
            'A fraction of a second saved repeatedly can produce a meaningful advantage over a full passage.',
            'High-level accuracy requires attention to capitalization, transitions, and subtle grammatical distinctions.',
            'The strongest results emerge when confidence remains steady despite unexpected changes in rhythm.',
            'Strategic practice targets weaknesses without sacrificing the consistency that supports peak performance.',
            'Competitive benchmarks are useful for training, although test formats can produce different results.'
        ]
    }
};

const elements = {
    passage: document.querySelector('#passage'),
    input: document.querySelector('#typing-input'),
    level: document.querySelector('#typing-level'),
    benchmarkDescription: document.querySelector('#benchmark-description'),
    status: document.querySelector('#typing-status'),
    timer: document.querySelector('#typing-timer'),
    progress: document.querySelector('#typing-progress'),
    finish: document.querySelector('#finish-test'),
    newPassage: document.querySelector('#new-passage'),
    reset: document.querySelector('#reset-test'),
    results: document.querySelector('#results'),
    performance: document.querySelector('#performance-note'),
    wpm: document.querySelector('#result-wpm'),
    netWpm: document.querySelector('#result-net-wpm'),
    comparison: document.querySelector('#result-comparison'),
    benchmarkReference: document.querySelector('#benchmark-reference'),
    accuracy: document.querySelector('#result-accuracy'),
    time: document.querySelector('#result-time'),
    errors: document.querySelector('#result-errors'),
    omissions: document.querySelector('#result-omissions'),
    corrected: document.querySelector('#result-corrected'),
    tryAgain: document.querySelector('#try-again')
};

let targetText = '';
let lastPassage = '';
let startedAt = null;
let timerInterval = null;
let finished = false;
let previousTypingErrors = 0;
let errorsMade = 0;
let errorsCorrected = 0;

function generatePassage(levelKey) {
    const sentences = [...typingLevels[levelKey].sentences];
    for (let index = sentences.length - 1; index > 0; index -= 1) {
        const swapIndex = Math.floor(Math.random() * (index + 1));
        [sentences[index], sentences[swapIndex]] = [sentences[swapIndex], sentences[index]];
    }

    const passage = sentences.slice(0, 4).join(' ');
    if (passage !== lastPassage) {
        lastPassage = passage;
        return passage;
    }

    const alternatePassage = [...sentences.slice(1, 4), sentences[0]].join(' ');
    lastPassage = alternatePassage;
    return alternatePassage;
}

function updateBenchmarkDescription() {
    const level = typingLevels[elements.level.value];
    elements.benchmarkDescription.textContent =
        `${level.name} practice reference: ${level.benchmark} WPM. This is a rough target, not a universal average.`;
}

function formatTime(milliseconds) {
    const totalSeconds = Math.floor(milliseconds / 1000);
    const minutes = Math.floor(totalSeconds / 60);
    const seconds = totalSeconds % 60;
    return `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
}

function renderPassage() {
    const typedText = elements.input.value;
    const fragment = document.createDocumentFragment();

    for (let index = 0; index < targetText.length; index += 1) {
        const character = targetText[index];
        const span = document.createElement('span');
        span.textContent = character;
        if (index < typedText.length) {
            span.className = typedText[index] === character ? 'is-correct' : 'is-incorrect';
        } else if (index === typedText.length && !finished) {
            span.className = 'is-current';
        }
        fragment.append(span);
    }

    elements.passage.replaceChildren(fragment);
}

function resetTest(chooseNewPassage = false) {
    if (timerInterval !== null) {
        window.clearInterval(timerInterval);
        timerInterval = null;
    }

    if (chooseNewPassage || !targetText) {
        targetText = generatePassage(elements.level.value);
    }

    startedAt = null;
    finished = false;
    previousTypingErrors = 0;
    errorsMade = 0;
    errorsCorrected = 0;
    elements.level.disabled = false;
    elements.input.value = '';
    elements.input.dataset.previousText = '';
    elements.input.maxLength = targetText.length;
    elements.input.disabled = false;
    elements.finish.disabled = true;
    elements.timer.textContent = '00:00';
    elements.progress.textContent = '0 characters typed';
    elements.status.textContent = 'Ready when you are. Start typing to begin.';
    elements.results.hidden = true;
    renderPassage();
}

function startTimer() {
    if (startedAt !== null || finished) {
        return;
    }
    startedAt = Date.now();
    elements.level.disabled = true;
    elements.finish.disabled = false;
    elements.status.textContent = 'Keep going — your time is running.';
    timerInterval = window.setInterval(() => {
        elements.timer.textContent = formatTime(Date.now() - startedAt);
    }, 200);
}

function compareTexts(target, typed) {
    const rows = Array.from({ length: target.length + 1 }, () => new Uint32Array(typed.length + 1));
    const directions = Array.from({ length: target.length + 1 }, () => new Uint8Array(typed.length + 1));

    for (let row = 1; row <= target.length; row += 1) {
        rows[row][0] = row;
        directions[row][0] = 2;
    }
    for (let column = 1; column <= typed.length; column += 1) {
        rows[0][column] = column;
        directions[0][column] = 3;
    }

    for (let row = 1; row <= target.length; row += 1) {
        for (let column = 1; column <= typed.length; column += 1) {
            const substitutionCost = rows[row - 1][column - 1] +
                (target[row - 1] === typed[column - 1] ? 0 : 1);
            const omissionCost = rows[row - 1][column] + 1;
            const extraCost = rows[row][column - 1] + 1;
            const minimum = Math.min(substitutionCost, omissionCost, extraCost);
            rows[row][column] = minimum;
            directions[row][column] = minimum === substitutionCost ? 1
                : minimum === omissionCost ? 2
                    : 3;
        }
    }

    let row = target.length;
    let column = typed.length;
    let substitutions = 0;
    let omissions = 0;
    let extras = 0;
    let correctCharacters = 0;

    while (row > 0 || column > 0) {
        const direction = directions[row][column];
        if (row > 0 && column > 0 && direction === 1) {
            if (target[row - 1] === typed[column - 1]) {
                correctCharacters += 1;
            } else {
                substitutions += 1;
            }
            row -= 1;
            column -= 1;
        } else if (row > 0 && (column === 0 || direction === 2)) {
            omissions += 1;
            row -= 1;
        } else {
            extras += 1;
            column -= 1;
        }
    }

    return { substitutions, omissions, extras, correctCharacters };
}

function countTypingErrors(typed) {
    let previousRow = new Uint32Array(typed.length + 1);
    let currentRow = new Uint32Array(typed.length + 1);

    for (let column = 1; column <= typed.length; column += 1) {
        previousRow[column] = column;
    }

    let minimum = typed.length;
    for (let row = 1; row <= targetText.length; row += 1) {
        currentRow[0] = row;
        for (let column = 1; column <= typed.length; column += 1) {
            currentRow[column] = Math.min(
                previousRow[column - 1] + (targetText[row - 1] === typed[column - 1] ? 0 : 1),
                previousRow[column] + 1,
                currentRow[column - 1] + 1
            );
        }
        minimum = Math.min(minimum, currentRow[typed.length]);
        [previousRow, currentRow] = [currentRow, previousRow];
    }

    return minimum;
}

function showResults(elapsedMilliseconds) {
    const comparison = compareTexts(targetText, elements.input.value);
    const minutes = elapsedMilliseconds / 60000;
    const safeMinutes = Math.max(minutes, 1 / 60);
    const grossWpm = Math.round((elements.input.value.length / 5) / safeMinutes);
    const netWpm = Math.round((comparison.correctCharacters / 5) / safeMinutes);
    const accuracy = Math.round((comparison.correctCharacters / targetText.length) * 100);
    const level = typingLevels[elements.level.value];
    const benchmarkDifference = netWpm - level.benchmark;
    const benchmarkComparison = benchmarkDifference === 0
        ? 'At the benchmark'
        : `${Math.abs(benchmarkDifference)} WPM ${benchmarkDifference > 0 ? 'above' : 'below'}`;
    const performance = accuracy < 80
        ? 'Focus on accuracy first; speed will build as your typing becomes more consistent.'
        : netWpm >= level.benchmark
            ? `You met or exceeded the ${level.name.toLowerCase()} practice benchmark. Keep building your accuracy.`
            : netWpm >= level.benchmark * 0.75
                ? `You are getting close to the ${level.name.toLowerCase()} practice benchmark. Keep practicing.`
                : `Keep practicing to build toward the ${level.name.toLowerCase()} practice benchmark.`;

    elements.wpm.textContent = String(grossWpm);
    elements.netWpm.textContent = `${netWpm} net WPM`;
    elements.comparison.textContent = benchmarkComparison;
    elements.benchmarkReference.textContent =
        `${level.name} reference: ${level.benchmark} WPM · compared using net speed`;
    elements.accuracy.textContent = String(accuracy);
    elements.time.textContent = formatTime(elapsedMilliseconds);
    elements.errors.textContent = String(errorsMade);
    elements.omissions.textContent = String(comparison.omissions);
    elements.corrected.textContent = String(errorsCorrected);
    elements.performance.textContent = performance;
    elements.results.hidden = false;
}

function finishTest() {
    if (startedAt === null || finished) {
        return;
    }

    finished = true;
    elements.input.disabled = true;
    elements.level.disabled = false;
    elements.finish.disabled = true;
    elements.status.textContent = 'Paragraph complete. Here is your review.';
    if (timerInterval !== null) {
        window.clearInterval(timerInterval);
        timerInterval = null;
    }
    const elapsedMilliseconds = Date.now() - startedAt;
    elements.timer.textContent = formatTime(elapsedMilliseconds);
    renderPassage();
    showResults(elapsedMilliseconds);
}

elements.input.addEventListener('input', () => {
    const currentText = elements.input.value;

    if (currentText.length > 0) {
        startTimer();
    }
    const currentTypingErrors = countTypingErrors(currentText);
    errorsMade += Math.max(0, currentTypingErrors - previousTypingErrors);
    errorsCorrected += Math.max(0, previousTypingErrors - currentTypingErrors);
    previousTypingErrors = currentTypingErrors;
    elements.progress.textContent = `${currentText.length} of ${targetText.length} characters typed`;
    renderPassage();

    if (currentText.length === targetText.length) {
        finishTest();
    }
});

elements.finish.addEventListener('click', finishTest);
elements.newPassage.addEventListener('click', () => resetTest(true));
elements.reset.addEventListener('click', () => resetTest());
elements.level.addEventListener('change', () => {
    updateBenchmarkDescription();
    resetTest(true);
});
elements.tryAgain.addEventListener('click', () => {
    resetTest(true);
    elements.input.focus();
});

updateBenchmarkDescription();
resetTest(true);
