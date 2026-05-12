const API_URL = "http://localhost:8080/tasks";

const taskToAdd = document.querySelector("#taskName");
const addTaskBtn = document.querySelector("#addTask");
const tasksList = document.querySelector("#tasksList");

addTaskBtn.addEventListener("click", () => {
    let taskTitle = taskToAdd.value.trim();
    if (taskTitle.length === 0){
        return;
    }
    createTask(taskTitle);
})

async function apiRequest(url, method = "GET", body = null) {
    const options = {
        method
    }
    if (body) {
        options.body = JSON.stringify(body);
        options.headers = { "Content-Type": "application/json" };
    } 
    try {
        const response = await fetch(url, options);

        if (method === "DELETE") return null;

        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);

        return await response.json();
    } catch (error) {
        console.log("Fetch error:", error);
    }
}


async function  createTask(taskTitle) {
    const task = {
        title: taskTitle,
        completed: false
    }
    const response = await apiRequest(API_URL, "POST", task);
    taskToAdd.value = "";
    fetchAndRenderTasks();
}


async function fetchAndRenderTasks() {
    const data = await apiRequest(API_URL);

    if (!data) return;

    tasksList.innerHTML = "";   

    if (data.length === 0) {
        const li = document.createElement("li");
        const span = document.createElement("span");
        const icon = document.createElement("i");
        li.id = "noTasks";
        span.classList.add("italic");
        span.textContent = "No tasks yet—go grab a coffee! ";
        li.appendChild(span);
        icon.textContent = "☕";
        li.appendChild(icon);
        tasksList.appendChild(li);
        return;
    }

    data.forEach(task => {
        const li = document.createElement("li");
        const span = document.createElement("span");
        const deleteButton = document.createElement("button");
        li.classList.add("task-item")
        span.classList.add("task-title");
        if (task.completed) {
            li.classList.add("completed");
        }
        li.dataset.id = task.id;
        li.dataset.completed = task.completed;
        span.textContent = task.title;
        li.appendChild(span);
        deleteButton.classList.add("delete-btn");
        deleteButton.textContent = "✕";
        li.appendChild(deleteButton);
        tasksList.appendChild(li);
    });
}


tasksList.addEventListener("click", async (e) => {
    const ti = e.target.closest(".task-item");
    
    if (!ti) return;

    const id = ti.dataset.id;

    if (e.target.classList.contains("delete-btn")) {
        await apiRequest(`${API_URL}/${id}`, "DELETE");
        fetchAndRenderTasks();
        return;
    }

    const completed = ti.dataset.completed === "true";
    const title = ti.querySelector(".task-title").textContent;
    const body = {
        title,
        completed: !completed
    }
    await apiRequest(`${API_URL}/${id}`, "PUT", body)

    fetchAndRenderTasks();
});

taskToAdd.addEventListener("keypress", (e) => {
    if (e.key === "Enter") addTaskBtn.click();
});

fetchAndRenderTasks();
