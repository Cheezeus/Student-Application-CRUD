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
            <h2>Delete Student</h2>
            <p>Are you sure you want to delete this student?</p>
        
            <!-- Display student details -->
            <div>
              <strong>NIM:</strong> {{ student.nim }}<br />
              <strong>First Name:</strong> {{ student.namaDepan }}<br />
              <strong>Last Name:</strong> {{ student.namaBelakang }}<br />
              <strong>Birth Date:</strong> {{ student.tanggalLahir }}<br />
              <strong>Gender:</strong> {{ student.jenisKelamin }}
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
            <router-link :to="{ name: 'update-student', params: { id: $route.params.id } }">
                <span>
                    Update Student
                </span>
            </router-link>
        </button>        
        <button @click="deleteStudent" class="btn btn-danger btn-md mx-2">
            <span>
                Delete Student
            </span>
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
    mounted() {
      // Fetch student details when the component is mounted
      this.fetchStudentDetails();
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
      async deleteStudent() {
        try {
            const nim = this.$route.params.id;
            if (!nim) {
                console.error('NIM parameter is missing.');
            return;
            }

            // Send a DELETE request to the backend to delete the student
            await axios.delete(`http://localhost:8080/api/studentapp/student/${nim}`);
    
            // Assuming a successful deletion, you can redirect to the student list or show a success message
            this.$router.push({ name: 'student-list' });
        } catch (error) {
            // Handle errors (show error message, log, etc.)
            console.error('Error deleting student:', error);
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