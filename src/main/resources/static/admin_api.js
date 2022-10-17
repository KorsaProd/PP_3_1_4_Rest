let url = "/api/admin";
let urlUser = "api/user";


function showAllUsers() {
    fetch(url)
        .then((response) => {
            return response.json();
        })
        .then((allUsers) => {
            let tbody = '';
            console.log(allUsers);
            tbody = document.getElementById('table_allUsers');
            allUsers.forEach((user) => {
                let roles = "";
                user.roles.forEach((role) => {
                    roles = roles + role.name.replace("ROLE_", "") + ' '
                })
                let tr = document.createElement('tr');
                tr.innerHTML =
                    '<td>' + user.id + '</td>' +
                    '<td>' + user.name + '</td>' +
                    '<td>' + user.lastName + '</td>' +
                    '<td>' + user.age + '</td>' +
                    '<td>' + roles + '</td>' +
                    '<td>' + '<button type="button" class="btn btn-info btn-sm" data-toggle="modal" ' +
                    'data-whatever="' + user.id + '" data-target="#editModal">Edit</button>' + '</td>' +
                    '<td>' + '<button type="button" class="btn btn-danger btn-sm" ' +
                    'data-toggle="modal" data-whatever="' + user.id + '" data-target="#deleteModal">Delete</button>' + '</td>';
                tbody.appendChild(tr);
            });
        });
}

function showUser() {
    fetch(urlUser)
        .then((response) => {
            return response.json();
        })
        .then((userActive) => {
            console.log(userActive);
            let tbody = document.getElementById('table_user');
            let roles = "";
            userActive.roles.forEach((role) => {
                roles = roles + role.name.replace("ROLE_", "") + ' '
            });
            let tr = document.createElement('tr');
            tr.innerHTML = '<td>' + userActive.id + '</td>' +
                '<td>' + userActive.name + '</td>' +
                '<td>' + userActive.lastName + '</td>' +
                '<td>' + userActive.age + '</td>' +
                '<td>' + roles + '</td>';
            tbody.appendChild(tr);
        })
}

async function showEditModal(id) {
    let user = await getUser(id);
    document.getElementById("editId").value = user.id;
    document.getElementById("editFirstname").value = user.username;
    document.getElementById("editLastname").value = user.lastName;
    document.getElementById("editAge").value = user.age;
    document.getElementById("editPassword").value = user.password;
    console.log(user);


    let selectEdit = document.getElementById('editRoles');
    let option = document.createElement('option')
    option.value = "ROLE_USER";
    option.text = "USER";
    let option2 = document.createElement('option')
    option2.value = "ROLE_ADMIN, ROLE_USER";
    option2.text = "ADMIN"
    selectEdit.appendChild(option);
    selectEdit.appendChild(option2);
}


function editUser() {
    let editForm = document.getElementById("editForm");
    let formData = new FormData(editForm);

    let user = {
        id: formData.get('id'),
        name: formData.get('username'),
        lastName: formData.get('lastName'),
        age: formData.get('age'),
        password: formData.get('password'),
        roles: formData.get('roles')
    }
    console.log(user)

    let response = fetch('api/admin/update', {
        method: 'PATCH',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then((response) => {
            document.getElementById('editForm').onsubmit;
        })

}


async function getUser(id) {
    let response = await fetch(url + '/' + id);
    return await response.json();
}

showUser();
showAllUsers();

