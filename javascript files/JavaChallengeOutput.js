const sampleOutputs = {
    'EvenNumbers.java': {
        input: '8',
        output: 'Enter the number\n0\n2\n4\n6\n8'
    },
    'FahrenheitToCelsius.java': {
        input: '98.6',
        output: 'Temperature in Celsius: 37.0'
    },
    'Factorial.java': {
        input: '5',
        output: 'Factorial of 5 is 120'
    },
    'OddEven.java': {
        input: '7',
        output: 'Enter the number\nThe number is odd'
    },
    'PrimeChecker.java': {
        input: '13',
        output: 'Enter the number\ntrue'
    },
    'GreatestCommonDivisor.java': {
        input: '12 18',
        output: 'Enter the first number\nEnter the second number\n6'
    },
    'MultiplicationTable.java': {
        input: '5',
        output: '5 x 1 = 5\n5 x 2 = 10\n5 x 3 = 15\n5 x 4 = 20\n5 x 5 = 25\n5 x 6 = 30\n5 x 7 = 35\n5 x 8 = 40\n5 x 9 = 45\n5 x 10 = 50'
    }
};

function getJavaFiles(challenge) {
    return [...challenge.querySelectorAll('a[href$=".java"]')];
}

function getFileNames(files) {
    return files.map((file) => file.href.split('/').pop());
}

function getOutputContent(fileNames) {
    const runnableFile = fileNames.find((fileName) => sampleOutputs[fileName]);
    return runnableFile ? sampleOutputs[runnableFile] : null;
}

function createOutputPanel(files) {
    const panel = document.createElement('div');
    const fileNames = getFileNames(files);
    const sample = getOutputContent(fileNames);
    const hasDependencies = files.length > 1;
    const commandFiles = fileNames.join(' ');

    panel.className = 'java-output-panel';
    panel.hidden = true;
    panel.setAttribute('aria-live', 'polite');

    const fileText = hasDependencies
        ? `Files used: ${fileNames.join(', ')}`
        : `File: ${fileNames[0]}`;
    const commandText = hasDependencies
        ? `Compile together with: javac ${commandFiles}`
        : `Run with: javac ${fileNames[0]} && java ${fileNames[0].replace('.java', '')}`;

    panel.innerHTML = `
        <p><strong>${fileText}</strong></p>
        <p>${commandText}</p>
    `;

    if (sample) {
        panel.insertAdjacentHTML(
            'beforeend',
            `<p>Example input: <strong>${sample.input}</strong></p><pre>${sample.output}</pre>`
        );
    } else {
        panel.insertAdjacentHTML(
            'beforeend',
            '<p>No saved sample output is available for this challenge yet. The command above shows how to run it locally. Programs that use Scanner may need input in the terminal.</p>'
        );
    }

    return panel;
}

function addOutputControls() {
    document.querySelectorAll('.project-content > ul > li').forEach((challenge) => {
        const files = getJavaFiles(challenge);
        if (!files.length) {
            return;
        }

        challenge.classList.add('java-challenge-item');
        const tools = document.createElement('div');
        const button = document.createElement('button');
        const panel = createOutputPanel(files);

        tools.className = 'java-challenge-tools';
        button.className = 'java-output-button';
        button.type = 'button';
        button.textContent = 'Show output';
        button.setAttribute('aria-expanded', 'false');
        button.addEventListener('click', () => {
            const isHidden = panel.hidden;
            panel.hidden = !isHidden;
            button.textContent = isHidden ? 'Hide output' : 'Show output';
            button.setAttribute('aria-expanded', String(isHidden));
        });

        tools.append(button, panel);
        challenge.append(tools);
    });
}

document.addEventListener('DOMContentLoaded', addOutputControls);
