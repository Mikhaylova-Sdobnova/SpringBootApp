let formNewUser = document.forms["formNewUser"]
let selectAdd = document.getElementById("new-roles");
const addedName = document.getElementById("new-name")
const addedLastName = document.getElementById("new-lastName")
const addedAge = document.getElementById("new-age")
const addedUsername = document.getElementById("new-username")
const addedPassword = document.getElementById("new-password")
const addButton = document.getElementById("addBtn")


function addUser(name, lastName, age, username, password) {

    let addedRoles = []
    for (let i = 0; i < formNewUser.roles.options.length; i++) {
        if (formNewUser.roles.options[i].selected)
            addedRoles.push({
                id: formNewUser.roles.options[i].value,
                role: "ROLE_" + formNewUser.roles.options[i].text
            });
    }
    fetch(URLUsers, {
        method: "POST",
        body: JSON.stringify({
            name: name,
            lastName: lastName,
            age: age,
            username: username,
            password: password,
            roles: addedRoles,
        }),
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => {
            if (res.ok) {
                console.log("User added")
                addedName.value = ''
                addedLastName.value = ''
                addedAge.value = ''
                addedUsername.value = ''
                addedPassword.value = ''
                selectAdd.selectedIndex = -1
                return res.json()
            } else {
                throw new Error("Failed to add")
            }
        })
        .then((user) => {
            const addedRow = `<tr>
                  <td>${user.id}</td>
                  <td>${user.name}</td>
                  <td>${user.lastName}</td>
                  <td>${user.age}</td>
                  <td>${user.username}</td>
                  <td>${addedRoles}</td>
                   <td>
                          <button type="button"
                          class="btn btn-primary"
                          data-bs-toogle="modal"
                          data-bs-target="#editModal"
                          onclick="editModal(${user.id})">
                                Edit
                            </button>
                        </td>
                        <td>
                            <button type="button"
                            class="btn btn-danger"
                            data-toggle="modal"
                            data-target="#deleteModal"
                            onclick="deleteModal(${user.id})">
                                Delete
                            </button>
                        </td>
                  </tr>`
            rows.insertAdjacentHTML("afterbegin", addedRow)
            getAllUsers();
        })
        .catch((error) => console.log(error))
}

function newUserRoles() {
    selectAdd.innerHTML = "";

    fetch("http://localhost:8080/api/admin/roles")
        .then(res => res.json())
        .then(data => {
            data.forEach(role => {
                let option = document.createElement("option");
                option.value = role.id;
                option.text = role.name.replace('ROLE_', '')
                selectAdd.appendChild(option);
            });
        })
}
window.addEventListener("load", newUserRoles)

addButton.addEventListener("click", (e) => {
    e.preventDefault()
    const newName = addedName.value
    const newLastName = addedLastName.value
    const newAge = addedAge.value
    const newUsername = addedUsername.value
    const newPassword = addedPassword.value

    addUser(newName, newLastName, newAge, newUsername, newPassword)
})