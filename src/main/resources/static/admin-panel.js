const URLGetUsers = 'http://localhost:8080/api/admin/users'
const rows = document.getElementById('tableBody')
let newRow = ''

function getAllUsers() {
    fetch(URLGetUsers)
        .then((res) => res.json())
        .then((users) => {
            users.map((user) => {
                return (newRow += `<tr>
                  <td>${user.id}</td>
                  <td>${user.name}</td>
                  <td>${user.lastName}</td>
                  <td>${user.age}</td>
                  <td>${user.username}</td>
                  </tr>`)
            })
            rows.innerHTML = newRow
        })
        .catch((error) => console.log(error))
}

getAllUsers()