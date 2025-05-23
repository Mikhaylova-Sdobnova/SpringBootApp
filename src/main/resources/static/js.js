// // const API = 'http://localhost:8080/api/admin/users'
// //
// // $(async function () {
// //     await getUsersTable()
// //     })
// //
// // const userFetchService = {
// //     head: {
// //         'Accept': 'application/json',
// //         'Content-Type': 'application/json',
// //         'Referer': null
// //     },
// //     findAllUsers: async () => await fetch(API),
// // }
// //
// // async function getUsersTable() {
// //     let table = $('#usersTable tbody')
// //     table.empty()
// //
// //     await userFetchService.findAllUsers()
// //         .then(res => res.json())
// //         .then(users => {
// //             users.forEach(user => {
// //                 let tableFill = `$(<tr>
// //             <td>${user.id}</td>
// //             <td>${user.name}</td>
// //             <td>${user.lastName}</td>
// //             <td>${user.age}</td>
// //             <td>${user.username}</td>
// //             </tr>)`
// //                 table.append(tableFill)
// //             })
// //         })
// }