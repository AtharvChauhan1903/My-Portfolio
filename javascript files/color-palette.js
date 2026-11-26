const elements = {
    harmony: document.querySelector('#harmony-select'),
    count: document.querySelector('#color-count'),
    format: document.querySelector('#format-select'),
    generate: document.querySelector('#generate-button'),
    palette: document.querySelector('#palette'),
    status: document.querySelector('#palette-status'),
    copyAll: document.querySelector('#copy-all-button'),
    export: document.querySelector('#export-button')
};

let colors = [];
let locked = [];

function hslToHex(hue, saturation, lightness) {
    const s = saturation / 100;
    const l = lightness / 100;
    const chroma = (1 - Math.abs(2 * l - 1)) * s;
    const segment = (((hue % 360) + 360) % 360) / 60;
    const secondary = chroma * (1 - Math.abs((segment % 2) - 1));
    let red = 0;
    let green = 0;
    let blue = 0;

    if (segment < 1) [red, green, blue] = [chroma, secondary, 0];
    else if (segment < 2) [red, green, blue] = [secondary, chroma, 0];
    else if (segment < 3) [red, green, blue] = [0, chroma, secondary];
    else if (segment < 4) [red, green, blue] = [0, secondary, chroma];
    else if (segment < 5) [red, green, blue] = [secondary, 0, chroma];
    else [red, green, blue] = [chroma, 0, secondary];

    const match = l - chroma / 2;
    return `#${[red, green, blue].map(channel =>
        Math.round((channel + match) * 255).toString(16).padStart(2, '0')
    ).join('').toUpperCase()}`;
}

function hexToRgb(hex) {
    const value = hex.replace('#', '');
    return {
        r: parseInt(value.slice(0, 2), 16),
        g: parseInt(value.slice(2, 4), 16),
        b: parseInt(value.slice(4, 6), 16)
    };
}

function formatColor(hex) {
    if (elements.format.value === 'rgb') {
        const { r, g, b } = hexToRgb(hex);
        return `rgb(${r}, ${g}, ${b})`;
    }
    return hex;
}

function getTextClass(hex) {
    const { r, g, b } = hexToRgb(hex);
    const channels = [r, g, b].map(channel => {
        const value = channel / 255;
        return value <= 0.03928
            ? value / 12.92
            : Math.pow((value + 0.055) / 1.055, 2.4);
    });
    const luminance = 0.2126 * channels[0] + 0.7152 * channels[1] + 0.0722 * channels[2];
    return luminance > 0.48 ? 'dark-text' : 'light-text';
}

function getHarmonyHues(count, harmony, baseHue) {
    if (harmony === 'analogous') {
        return Array.from({ length: count }, (_, index) =>
            baseHue - 36 + (72 / Math.max(count - 1, 1)) * index
        );
    }
    if (harmony === 'complementary') {
        return Array.from({ length: count }, (_, index) =>
            baseHue + (index % 2 === 0 ? -12 : 168) + Math.floor(index / 2) * 18
        );
    }
    if (harmony === 'split-complementary') {
        const anchors = [baseHue - 150, baseHue, baseHue + 150];
        return Array.from({ length: count }, (_, index) =>
            anchors[Math.min(index, anchors.length - 1)] + (index - 2) * 8
        );
    }
    if (harmony === 'triadic') {
        const anchors = [baseHue, baseHue + 120, baseHue + 240];
        return Array.from({ length: count }, (_, index) => anchors[index % 3]);
    }
    return Array.from({ length: count }, () => baseHue);
}

function generatePalette() {
    const count = Number(elements.count.value);
    const baseHue = Math.floor(Math.random() * 360);
    const hues = getHarmonyHues(count, elements.harmony.value, baseHue);
    const nextColors = [];

    for (let index = 0; index < count; index += 1) {
        if (locked[index] && colors[index]) {
            nextColors[index] = colors[index];
            continue;
        }

        let saturation;
        let lightness;
        if (elements.harmony.value === 'monochromatic') {
            saturation = 58 + Math.random() * 18;
            lightness = 24 + (68 / Math.max(count - 1, 1)) * index + Math.random() * 5;
        } else {
            saturation = 58 + Math.random() * 28;
            lightness = 38 + Math.random() * 28;
        }
        nextColors[index] = hslToHex(hues[index], saturation, lightness);
    }

    colors = nextColors;
    renderPalette();
    elements.status.textContent = locked.some(Boolean)
        ? 'Palette updated. Your locked colors stayed in place.'
        : 'Fresh palette generated.';
}

function renderPalette() {
    const fragment = document.createDocumentFragment();
    elements.palette.style.setProperty('--color-count', String(colors.length));

    colors.forEach((hex, index) => {
        const swatch = document.createElement('article');
        swatch.className = 'swatch';
        swatch.style.backgroundColor = hex;

        const info = document.createElement('div');
        info.className = `swatch-info ${getTextClass(hex)}`;

        const code = document.createElement('strong');
        code.className = 'swatch-code';
        code.textContent = formatColor(hex);

        const buttons = document.createElement('div');
        buttons.className = 'swatch-buttons';

        const copyButton = document.createElement('button');
        copyButton.type = 'button';
        copyButton.className = 'copy-button';
        copyButton.textContent = 'Copy';
        copyButton.setAttribute('aria-label', `Copy ${formatColor(hex)}`);
        copyButton.addEventListener('click', () => copyText(formatColor(hex), `${formatColor(hex)} copied.`));

        const lockButton = document.createElement('button');
        lockButton.type = 'button';
        lockButton.className = 'lock-button';
        lockButton.setAttribute('aria-pressed', String(Boolean(locked[index])));
        lockButton.setAttribute('aria-label', `${locked[index] ? 'Unlock' : 'Lock'} ${formatColor(hex)}`);
        lockButton.innerHTML = `<span>${locked[index] ? 'Locked' : 'Lock'}</span>`;
        lockButton.addEventListener('click', () => {
            locked[index] = !locked[index];
            renderPalette();
            elements.status.textContent = locked[index]
                ? `${formatColor(hex)} is locked.`
                : `${formatColor(hex)} is unlocked.`;
        });

        buttons.append(copyButton, lockButton);
        info.append(code, buttons);
        swatch.append(info);
        fragment.append(swatch);
    });

    elements.palette.replaceChildren(fragment);
}

async function copyText(text, successMessage) {
    try {
        await navigator.clipboard.writeText(text);
    } catch (error) {
        const textarea = document.createElement('textarea');
        textarea.value = text;
        textarea.style.position = 'fixed';
        textarea.style.opacity = '0';
        document.body.append(textarea);
        textarea.select();
        document.execCommand('copy');
        textarea.remove();
    }
    elements.status.textContent = successMessage;
}

elements.generate.addEventListener('click', generatePalette);

elements.count.addEventListener('change', () => {
    const count = Number(elements.count.value);
    colors = colors.slice(0, count);
    locked = locked.slice(0, count);
    while (colors.length < count) {
        colors.push('#14B8A6');
        locked.push(false);
    }
    generatePalette();
});

elements.format.addEventListener('change', renderPalette);

elements.copyAll.addEventListener('click', () => {
    copyText(colors.map(formatColor).join(', '), 'All palette colors copied.');
});

elements.export.addEventListener('click', () => {
    const css = `:root {\n${colors.map((hex, index) =>
        `  --palette-${index + 1}: ${formatColor(hex)};`
    ).join('\n')}\n}`;
    copyText(css, 'CSS variables copied to your clipboard.');
});

document.addEventListener('keydown', event => {
    if (event.code === 'Space' &&
        event.target.tagName !== 'INPUT' &&
        event.target.tagName !== 'TEXTAREA' &&
        event.target.tagName !== 'SELECT' &&
        event.target.tagName !== 'BUTTON') {
        event.preventDefault();
        generatePalette();
    }
});

generatePalette();
