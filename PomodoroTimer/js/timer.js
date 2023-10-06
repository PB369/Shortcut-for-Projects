let timer = document.querySelector("#timer")
let time = document.querySelector("#time")
let reduceTime = document.querySelector("#reduce-time")
let increaseTime = document.querySelector("#increase-time")
let startStopButton = document.querySelector("#start-stop-button")
let cancelResetButton = document.querySelector("#cancel-reset-button")
let activeOption = false
let alarmIcon = document.querySelector("#alarm-icon")
let closeIcon = document.querySelector("#close-icon")
let alarmsPopUp = document.querySelector("#alarms-popup")
let alarms = document.querySelectorAll(".alarms")

function toggleOptionsButtons(){

  activeOption = !activeOption
  if(activeOption){
    alarmIcon.style.display = "none"
    closeIcon.style.display = "inline-block"
    alarmsPopUp.style.display = "flex"
    timer.style.filter = "blur(20px)"

  }
  else {
    alarmIcon.style.display = "inline-block"
    closeIcon.style.display = "none"
    alarmsPopUp.style.display = "none"
    timer.style.filter = "blur(0)"
  }
}

function selectAlarm(selectedAlarm){
  alarms.forEach(alarm => {
    alarm.removeAttribute("id")
  })
  selectedAlarm.setAttribute("id", "selected-alarm")
  let alarmName = selectedAlarm.textContent.toLowerCase()
  alarmAudio = new Audio(`../audio/${alarmName}.mp3`)
  playAlarmAudio(alarmAudio)
}

let timeAmount = 0
let countdownTime = 0
let timerIsStopped = true

startStopButton.addEventListener("click", ()=>{
  let checkAlarmSelection = document.querySelector("#selected-alarm")
  if(checkAlarmSelection != null){
    if(startStopButton.textContent == "Start"){
      startTimer()
    }
    else{
      stopTimer()
    }
  }
  else{
    alert("First you need to choose an alarm.")
  }
})

cancelResetButton.addEventListener("click", () => {
  if(cancelResetButton.textContent == "Reset"){
    resetTimer()
  }
  else{
    cancelTimer()
  }
})

increaseTime.addEventListener("click", () => {
  if(timeAmount < 60){
    timeAmount += 5
    countdownTime = timeAmount * 60;
    renderTime()
  }
})

reduceTime.addEventListener("click", () => {
  if(timeAmount >= 5){
    timeAmount -= 5
    countdownTime = timeAmount * 60;
    renderTime()
  }
})