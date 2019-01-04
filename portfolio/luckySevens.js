function clearErrors() {
  for (var loopCounter = 0;
    loopCounter < document.forms["luckySevens"].elements.length;
    loopCounter++) {
    if (document.forms["luckySevens"].elements[loopCounter]
     .parentElement.className.indexOf("has-") != -1) {
        document.forms["luckySevens"].elements[loopCounter].parentElement.className = "form-group";
    }
  }
}

/*returns a number betwee 2-12 with probabiltiy of rolling 2 dice*/
function rollDice() {
  return Math.floor(Math.random() * 6) + Math.floor(Math.random() * 6) + 2;
}


/* validateBets checks to see if bet is correct and then runs through
    game cycle until all money is gone.*/
function validateBet() {
  clearErrors();
  totalRolls=0;

  bestRoll = 0;
  var bet = Number(document.forms["luckySevens"]["bet"].value);
  maxWon=bet;
  /*check if bet is valid*/
  if (isNaN(bet) || bet == "" || bet<1){
    alert("You must enter a valid Bet that is larger than 0")
    document.forms["luckySevens"]["bet"].parentElement.className = "form-group has-error";
    document.forms["luckySevens"]["bet"].focus();
    return false;
  }
  //beginning game play
  oBet = bet;
  while (bet>0) {
    var roll = rollDice();
    totalRolls++;
    if (roll==7) {
      bet = bet+4;
      if (bet > maxWon) {
        maxWon = bet;
        bestRoll = totalRolls;
      }
    }
    else {
      bet--;
    }
  }
  //game over, display results.
  document.getElementById("results").style.display = "block";
  document.getElementById("play").innerText = "Play Again";
  document.getElementById("startingBet").innerText = oBet;
  document.getElementById("totalRolls").innerText = totalRolls;
  document.getElementById("maxWon").innerText = maxWon;
  document.getElementById("bestRoll").innerText = bestRoll;
  return false;
}
