const roundForm = document.querySelector('#round-form');
const roundCountInput = document.querySelector('#round-count');
const formMessage = document.querySelector('#form-message');
const modeNameElement = document.querySelector('#mode-name');
const modeToggleButton = document.querySelector('#mode-toggle');
const rulesLiveLabel = document.querySelector('#rules-live-label');
const normalRulesElement = document.querySelector('#normal-rules');
const reverseRulesElement = document.querySelector('#reverse-rules');
const gameStatus = document.querySelector('#game-status');
const resultMessage = document.querySelector('#result-message');
const playerScoreElement = document.querySelector('#player-score');
const computerScoreElement = document.querySelector('#computer-score');
const roundsLeftElement = document.querySelector('#rounds-left');
const playerChoiceElement = document.querySelector('#player-choice');
const computerChoiceElement = document.querySelector('#computer-choice');
const playerIconElement = document.querySelector('#player-icon');
const computerIconElement = document.querySelector('#computer-icon');
const matchupElement = document.querySelector('#matchup');
const playerRevealedChoice = document.querySelector('.player-revealed-choice');
const computerRevealedChoice = document.querySelector('.computer-revealed-choice');
const choicesElement = document.querySelector('.choices');
const choiceButtons = document.querySelectorAll('.choice-button');

const choices = ['rock', 'paper', 'scissors'];
const winningChoices = {
	rock: 'scissors',
	paper: 'rock',
	scissors: 'paper'
};

const reverseWinningChoices = {
	rock: 'paper',
	paper: 'scissors',
	scissors: 'rock'
};

const choiceSymbols = {
	rock: '✊',
	paper: '✋',
	scissors: '✌️'
};

let game = null;
let audioContext = null;
let selectedMode = 'normal';

function updateModeDisplay() {
	const reverseMode = selectedMode === 'reverse';
	modeNameElement.textContent = reverseMode ? 'Reverse rules' : 'Normal rules';
	modeToggleButton.textContent = reverseMode ? 'Switch to normal mode' : 'Switch to reverse mode';
	rulesLiveLabel.textContent = reverseMode ? 'Reverse mode active' : 'Normal mode active';
	normalRulesElement.classList.toggle('active', !reverseMode);
	reverseRulesElement.classList.toggle('active', reverseMode);
}

function getAudioContext() {
	if (!audioContext) {
		const AudioContextConstructor = window.AudioContext || window.webkitAudioContext;
		if (!AudioContextConstructor) {
			return null;
		}
		audioContext = new AudioContextConstructor();
	}

	if (audioContext.state === 'suspended') {
		audioContext.resume();
	}

	return audioContext;
}

function playTone(frequency, startTime, duration, volume, type = 'sine') {
	const context = getAudioContext();
	if (!context) {
		return;
	}

	const oscillator = context.createOscillator();
	const gain = context.createGain();
	oscillator.type = type;
	oscillator.frequency.value = frequency;
	gain.gain.setValueAtTime(0.001, startTime);
	gain.gain.exponentialRampToValueAtTime(volume, startTime + 0.02);
	gain.gain.exponentialRampToValueAtTime(0.001, startTime + duration);
	oscillator.connect(gain);
	gain.connect(context.destination);
	oscillator.start(startTime);
	oscillator.stop(startTime + duration);
}

function playSound(soundName) {
	const context = getAudioContext();
	if (!context) {
		return;
	}

	const now = context.currentTime;
	const sounds = {
		pointWin: [[523, 0, 0.16], [659, 0.13, 0.2]],
		pointLoss: [[330, 0, 0.16], [247, 0.13, 0.22]],
		tie: [[392, 0, 0.18], [392, 0.2, 0.18]],
		gameWin: [[523, 0, 0.16], [659, 0.14, 0.16], [784, 0.28, 0.32]],
		gameLoss: [[330, 0, 0.2], [262, 0.18, 0.2], [196, 0.36, 0.35]]
	};

	(sounds[soundName] || []).forEach(([frequency, offset, duration]) => {
		playTone(frequency, now + offset, duration, 0.08, soundName === 'tie' ? 'triangle' : 'sine');
	});
}

function setChoiceButtons(enabled) {
	choiceButtons.forEach((button) => {
		button.disabled = !enabled;
	});
}

function updateScoreboard() {
	playerScoreElement.textContent = game.playerScore;
	computerScoreElement.textContent = game.computerScore;
	roundsLeftElement.textContent = game.roundsLeft;
}

function getComputerChoice() {
	return choices[Math.floor(Math.random() * choices.length)];
}

function getRoundResult(playerChoice, computerChoice) {
	if (playerChoice === computerChoice) {
		return 'tie';
	}

	const activeRules = game.mode === 'reverse' ? reverseWinningChoices : winningChoices;
	return activeRules[playerChoice] === computerChoice ? 'player' : 'computer';
}

function resetReveal() {
	matchupElement.classList.remove('revealing', 'loser-hidden');
	playerRevealedChoice.classList.remove('is-loser');
	computerRevealedChoice.classList.remove('is-loser');
	playerIconElement.textContent = '?';
	computerIconElement.textContent = '?';
}

function revealChoices(playerChoice, computerChoice) {
	playerChoiceElement.textContent = playerChoice;
	computerChoiceElement.textContent = computerChoice;
	playerIconElement.textContent = choiceSymbols[playerChoice];
	computerIconElement.textContent = choiceSymbols[computerChoice];
	matchupElement.classList.add('revealing');
}

function markLosingChoice(roundResult) {
	if (roundResult === 'player') {
		computerRevealedChoice.classList.add('is-loser');
	} else if (roundResult === 'computer') {
		playerRevealedChoice.classList.add('is-loser');
	}
	matchupElement.classList.add('loser-hidden');
}

function finishGame() {
	if (game.playerScore === game.computerScore) {
		game.suddenDeath = true;
		game.roundsLeft = 'GP';
		updateScoreboard();
		gameStatus.textContent = 'Game point: first player to score wins.';
		resultMessage.textContent = 'The match is tied. Choose a move for the game point.';
		return;
	}

	const winner = game.playerScore > game.computerScore ? 'You win!' : 'Computer wins!';
	game.finished = true;
	gameStatus.textContent = 'Match complete.';
	resultMessage.textContent = `${winner} Final score: ${game.playerScore} - ${game.computerScore}.`;
	setChoiceButtons(false);
	playSound(game.playerScore > game.computerScore ? 'gameWin' : 'gameLoss');
	window.alert(`${winner} Final score: ${game.playerScore} - ${game.computerScore}.`);
}

function resolveRound(playerChoice, computerChoice, roundResult) {
	markLosingChoice(roundResult);

	if (roundResult === 'player') {
		game.playerScore += 1;
		resultMessage.textContent = 'You win this round. +1 point';
		playSound('pointWin');
	} else if (roundResult === 'computer') {
		game.computerScore += 1;
		resultMessage.textContent = 'The computer wins this round. +1 point';
		playSound('pointLoss');
	} else {
		resultMessage.textContent = 'Tie round. No points awarded.';
		playSound('tie');
	}

	if (game.suddenDeath) {
		game.finished = roundResult !== 'tie';
		updateScoreboard();
		if (game.finished) {
			const winner = game.playerScore > game.computerScore ? 'You win!' : 'Computer wins!';
			gameStatus.textContent = 'Game point decided.';
			resultMessage.textContent = `${winner} You took the game point.`;
			setChoiceButtons(false);
			playSound(game.playerScore > game.computerScore ? 'gameWin' : 'gameLoss');
			window.alert(`${winner} The game point decides the match.`);
		} else {
			gameStatus.textContent = 'Still tied. Play another game point.';
			setChoiceButtons(true);
		}
		return;
	}

	game.roundsLeft -= 1;
	updateScoreboard();
	if (game.roundsLeft === 0) {
		finishGame();
	} else {
		gameStatus.textContent = `${game.roundsLeft} round${game.roundsLeft === 1 ? '' : 's'} remaining.`;
		setChoiceButtons(true);
	}
}

function playRound(playerChoice) {
	if (!game || game.finished) {
		return;
	}

	setChoiceButtons(false);
	gameStatus.textContent = 'Rock, paper, scissors...';
	resultMessage.textContent = 'Show your move!';
	choicesElement.classList.add('is-shaking');
	resetReveal();

	const computerChoice = getComputerChoice();
	const roundResult = getRoundResult(playerChoice, computerChoice);

	window.setTimeout(() => {
		choicesElement.classList.remove('is-shaking');
		revealChoices(playerChoice, computerChoice);
		window.setTimeout(() => {
			matchupElement.classList.remove('revealing');
			resolveRound(playerChoice, computerChoice, roundResult);
		}, 1000);
	}, 900);
}

roundForm.addEventListener('submit', (event) => {
	event.preventDefault();
	const requestedRounds = Number(roundCountInput.value);

	if (!Number.isInteger(requestedRounds) || requestedRounds < 1 || requestedRounds > 99) {
		formMessage.textContent = 'Choose a whole number between 1 and 99.';
		return;
	}

	game = {
		playerScore: 0,
		computerScore: 0,
		roundsLeft: requestedRounds,
		mode: selectedMode,
		suddenDeath: false,
		finished: false
	};

	formMessage.textContent = '';
	resetReveal();
	playerChoiceElement.textContent = 'Waiting';
	computerChoiceElement.textContent = 'Waiting';
	resultMessage.textContent = 'Make your move.';
	gameStatus.textContent = `${requestedRounds} round${requestedRounds === 1 ? '' : 's'} ready.`;
	updateScoreboard();
	setChoiceButtons(true);
});

choiceButtons.forEach((button) => {
	button.addEventListener('click', () => playRound(button.dataset.choice));
});

modeToggleButton.addEventListener('click', () => {
	if (game && !game.finished) {
		formMessage.textContent = 'Finish the current match before changing modes.';
		return;
	}

	selectedMode = selectedMode === 'normal' ? 'reverse' : 'normal';
	updateModeDisplay();
	formMessage.textContent = `${modeNameElement.textContent} selected. Start a new game to play.`;
});

updateModeDisplay();
setChoiceButtons(false);
