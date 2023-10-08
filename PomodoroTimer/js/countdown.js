let resetTimer = () => {
  countdownTime = timeAmount * 60;
  renderTime()
}

let cancelTimer = () => {
  stopTimer()
  countdownTime = 0
  time.textContent = "00 : 00"
  cancelResetButton.textContent = "Reset"
}

let stopTimer = () => {
  timerIsStopped = true
  if(timerIsStopped){
    startStopButton.textContent = "Start"
    clearInterval(countTimer)
  }
}

let startTimer = () =>{
  if(timerIsStopped){
    timerIsStopped = false
    startStopButton.textContent = "Stop"
    cancelResetButton.textContent = "Cancel"
    countTimer = setInterval(runCountdown, 1000)
  }
}

let runCountdown = () =>{
  if(countdownTime === 0){
    let selectedAlarm = document.querySelector("#selected-alarm")
    let alarmName = selectedAlarm.textContent.toLowerCase()
    alarmAudio = new Audio(`/PomodoroTimer/audio/${alarmName}.mp3`)
    stopTimer()
    playAlarmAudio(alarmAudio)
    cancelResetButton.textContent = "Reset"
  }
  else {
    countdownTime -= 1
    renderTime()
  }
}

let playAlarmAudio = (alarmAudio) => {
  alarmAudio.play()
}

let stopAlarmAudio = (alarmAudio) => {
  alarmAudio.pause()
  alarmAudio.currentTime = 0
}

let renderTime = ()=>{
  time.textContent = timeString()
}

let timeString = () => {
  let minutes = String(Math.trunc(countdownTime / 60))
  let seconds = String(countdownTime % 60);
  
  if(minutes.length === 1){
    minutes = "0" + minutes
  }
  if(seconds.length === 1){
    seconds = "0" + seconds
  }
  let timeFormat = minutes + " : " + seconds
  return timeFormat
}