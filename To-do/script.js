let input = document.querySelector("#task__input")
let addNewTaskButton = document.querySelector("#add__button")
let tasks = document.querySelector("#tasks")

function addNewTask(event){
  event.preventDefault

  let task = input.value
  let taskUl = document.createElement("ul")
  let taskLi = document.createElement("li")
  let taskSpan = document.createElement("span")
  
  let editTaskButton = document.createElement("button")
  editTaskButton.classList.add("editTaskButton")
  editTaskButton.textContent = "Editar"
  editTaskButton.addEventListener("click", (event) => {
    editTask(event)
  })
  
  let removeTaskButton = document.createElement("button")
  removeTaskButton.classList.add("removeTaskButton")
  removeTaskButton.textContent = "X"
  removeTaskButton.addEventListener("click", (event) => {
    removeTask(event)
  })
 
  taskSpan.textContent = task
  taskLi.classList.add("task")
  taskLi.appendChild(taskSpan)
  taskLi.appendChild(editTaskButton)
  taskLi.appendChild(removeTaskButton)
  taskUl.appendChild(taskLi)
  tasks.appendChild(taskUl)
  console.log(tasks)
  input.value = ""

}

addNewTaskButton.addEventListener("click", (event) => {
  addNewTask(event)
})

function editTask(event) {
  event.preventDefault

  let liOfTask = event.target.parentElement
  let inputToEditTask = document.createElement("input")
  let finishEditButton = document.createElement("button")
  liOfTask.appendChild(inputToEditTask)
  liOfTask.appendChild(finishEditButton)
  finishEditButton.textContent = "V"
  finishEditButton.addEventListener("click", (event) => {
    event.preventDefault

    liOfTask.textContent = inputToEditTask.value
    
    finishEditButton.remove()
    inputToEditTask.remove()
  })
}

function removeTask(event) {
  event.preventDefault
  
  let liOfTask = event.target.parentElement
  let ulOfTask = liOfTask.parentElement
  ulOfTask.remove()
}