<template>
    <nav class="navbar navbar-expand-lg bg-dark mb-5">
        <div class="container-fluid">
            <span class="py-2 px-3">
                <h4>Student Application</h4>
            </span>
        </div>
    </nav>

<div class="container">
    <div class="row info mb-5 py-5">
        <h2>Student Detail</h2>
        <div>
            <strong>Nomor Induk Mahasiswa (NIM):</strong> {{ student.nim }}
        </div>
        <div>
            <strong>Nama Depan:</strong> {{ student.namaDepan }}
        </div>
        <div>
            <strong>Nama Belakang:</strong> {{ student.namaBelakang }}
        </div>
        <div>
            <strong>Nama Lengkap:</strong> {{ student.namaLengkap }}
        </div>
        <div>
            <strong>Tanggal Lahir:</strong> {{ student.tanggalLahir }}
        </div>
        <div>
            <strong>Usia:</strong> {{ student.usia }}
        </div>
        <div>
            <strong>Jenis Kelamin:</strong> {{ student.jenisKelamin }}
        </div>
    </div>

    <button type="button" class="btn btn-primary btn-md mx-2">
        <router-link :to="{ name: 'student-list' }">
            <span>
                Back to Student List
            </span>
        </router-link>
    </button>
    <button type="button" class="btn btn-success btn-md mx-2">
        <router-link :to="{ name: 'update-student', params: { id: student.nim } }">
            <span>
                Update Student
            </span>
        </router-link>
    </button>
    <button type="button" class="btn btn-danger btn-md mx-2">
        <router-link :to="{ name: 'delete-student', params: { id: student.nim } }">
            <span>
                Delete Student
            </span>
        </router-link>
    </button>


</div>
</template>

<script>
    import axios from 'axios';

    export default {
    data() {
        return {
            student: {
            nim: null,
            namaDepan: '',
            namaBelakang: '',
            tanggalLahir: null,
            jenisKelamin: '',
            },
        };
    },
    created() {
        // Fetch student details from your backend API
        const nim = this.$route.params.id;

        if (nim) {
            this.fetchStudentDetails(nim);
        } else {
            console.error('NIM parameter is missing.');
        }
    },
    methods: {
        async fetchStudentDetails() {
                try {
                    const nim = this.$route.params.id;
                    if (!nim) {
                        console.error('NIM parameter is missing.');
                    return;
                    }

                    const response = await axios.get(`http://localhost:8080/api/studentapp/student/${nim}`);
                    this.student = response.data;
                } catch (error) {
                    console.error('Error fetching student details:', error);
                }
            },
        },
    };
</script>

<style scoped>
    .info {
        background-color: #d9d9d9;
        border-radius: 20px;
    }

    span {
        color:white;
    }
</style>