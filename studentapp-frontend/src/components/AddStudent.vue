<template>
    <nav class="navbar navbar-expand-lg bg-dark mb-5">
        <div class="container-fluid">
            <span class="py-2 px-3">
                <h4>Student Application</h4>
            </span>
        </div>
    </nav>

    <div class="container">
        <div class="header">
            <h2>Add New Student</h2>
        </div>
        
        <div class="bodyForm py-3">
            <form @submit.prevent="addStudent">
                <div class="row px-5 justify-content-center">
    
                    <div class="row">
                        <div class="mb-3 px-5">
                            <label for="nim" class="form-label">NIM:</label>
                            <input type="text" v-model="student.nim" class="form-control" required />
                        </div>
                    </div>
    
                    <div class="row px-0">
                        <div class="col-6">
                            <div class="mb-3 px-5">
                                <label for="namaDepan" class="form-label">Nama Depan:</label>
                                <input type="text" v-model="student.namaDepan" class="form-control" required />
                            </div>
                
                            <div class="mb-3 px-5">
                                <label for="namaBelakang" class="form-label">Nama Belakang:</label>
                                <input type="text" v-model="student.namaBelakang" class="form-control"/>
                            </div>
                        </div>
        
                        <div class="col-6 py-3">
                            <div class="mb-3 px-5">
                                <label for="tanggalLahir">Tanggal Lahir:</label>
                                <input type="date" v-model="student.tanggalLahir" class="form-control" required />
                            </div>
                
                            <div class="mb-3 px-5">
                                <label for="jenisKelamin">Jenis Kelamin:</label>
                                <select v-model="student.jenisKelamin" id="jenisKelamin" class="form-select" required>
                                    <option value="Laki-laki">Laki-laki</option>
                                    <option value="Perempuan">Perempuan</option>
                                </select>
                            </div>
                        </div>
                    </div>
    
                </div>

                <div class="my-3 px-5">
                    <button type="submit" class="btn btn-success btn-md">Add Student</button>
                </div>
            </form>    
        </div>

        <div class="my-3 px-5">
            <button type="button" class="btn btn-primary btn-md">
                <router-link :to="{ name: 'student-list' }">
                    <span>
                        Back to Student List
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
        student: {
            nim: '',
            namaDepan: '',
            namaBelakang: '',
            tanggalLahir: '',
            jenisKelamin: '',
        },
        loading: false,
        };
    },
    methods: {
        addStudent() {

        // Set loading to true when the request starts
        this.loading = true;

        axios.post('http://localhost:8080/api/studentapp/student', this.student)
            .then(response => {
            console.log('Student added successfully:', response.data);

            // Reset the form after successful submission
            this.student = {
                nim: '',
                namaDepan: '',
                namaBelakang: '',
                tanggalLahir: '',
                jenisKelamin: '',
            };

            // Navigate back to the student list
            this.$router.push({ name: 'student-list' });
            })
            .catch(error => {
                console.error('Error adding student:', error);
                console.log('Error Response:', error.response);

            })
            .finally(() => {
            // Set loading back to false regardless of success or failure
            this.loading = false;
            });
        },
    },
    };
</script>

<style scoped>
    .bodyForm {
        background-color: #d9d9d9;
        border-radius: 20px;
    }

    span {
        color:white;
    }

</style>