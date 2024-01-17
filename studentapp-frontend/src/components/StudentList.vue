<template>
    <nav class="navbar navbar-expand-lg bg-dark mb-5">
        <div class="container-fluid">
            <span class="py-2 px-3">
                <h4>Student Application</h4>
            </span>
        </div>
    </nav>

    <div class="container px-5">
        <h1>Student List</h1>
        <div class="py-3">
            <table class="table">
            <thead>
                <tr class="table-dark">
                    <th scope="col">NIM</th>
                    <th scope="col">Nama Lengkap</th>
                    <th scope="col">Usia</th>
                    <th scope="col">Jenis Kelamin</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="student in students" :key="student.nim">
                    <td>{{ student.nim }}</td>
                    <td>{{ student.namaLengkap }}</td>
                    <td>{{ student.usia }}</td>
                    <td>{{ student.jenisKelamin }}</td>
                    <td>
                        <button type="button" class="btn btn-primary btn-sm">
                            <router-link :to="{ name: 'student-detail', params: { id: student.nim } }">
                                <span>
                                    Details
                                </span>
                            </router-link>
                        </button>
                    </td>
                </tr>
            </tbody>
            </table>
        </div>

        <div>
            <button type="button" class="btn btn-primary">
                <router-link :to="{ name: 'add-student' }">
                    <span>
                        Add Student
                    </span>
                </router-link>
            </button>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
    data() {
        return {
        students: [],
        };
    },
    created() {
        axios.get('http://localhost:8080/api/studentapp/student-list')
        .then(response => {
            this.students = response.data;
        })
        .catch(error => {
            console.error('Error fetching students', error);
        });
    },
};
</script>

<style>
    span {
        color:white;
    }

    table {
        border: 2px solid grey;
    }

</style>
