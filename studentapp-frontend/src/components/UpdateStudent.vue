<!-- src/views/UpdateStudent.vue -->
<template>
    <nav class="navbar navbar-expand-lg bg-dark mb-5">
        <div class="container-fluid">
            <span class="py-2 px-3">
                <h4>Student Application</h4>
            </span>
        </div>
    </nav>

    <div class="container">
      <h2>Update Student</h2>
      <form @submit.prevent="updateStudent" class="px-5 justify-content-center">
        <div class="row">
            <h5>NIM: {{ $route.params.id }}</h5>
        </div>

        <div class="row pb-4">
            <label for="namaDepan" class="form-label">First Name:</label>
            <input v-model="student.namaDepan" type="text" id="namaDepan" class="form-control" required />
      
            <label for="namaBelakang" class="form-label">Last Name:</label>
            <input v-model="student.namaBelakang" type="text" id="namaBelakang" class="form-control" />
      
            <label for="tanggalLahir" class="form-label">Birth Date:</label>
            <input v-model="student.tanggalLahir" type="date" id="tanggalLahir" class="form-control" required />
      
            <label for="jenisKelamin" class="form-label">Gender:</label>
            <select v-model="student.jenisKelamin" id="jenisKelamin" class="form-control" required>
              <option value="Laki-laki">Laki-laki</option>
              <option value="Perempuan">Perempuan</option>
            </select>
        </div>
        
        <div class="pb-5">
            <button type="button" class="btn btn-primary btn-md mx-2">
                <router-link :to="{ name: 'student-list' }">
                    <span>
                        Back to Student List
                    </span>
                </router-link>
            </button>
    
            <button type="submit" class="btn btn-success btn-md mx-2">
                Update Student
            </button>
    
            <button type="button" class="btn btn-danger btn-md mx-2">
                <router-link :to="{ name: 'delete-student', params: { id: $route.params.id } }">
                    <span>Delete Student</span>
                </router-link>
            </button>
        </div>
      </form>
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
    };
  },
  methods: {
    async updateStudent() {
      try {
        const nim = this.$route.params.id;
        if (!nim) {
          console.error('NIM parameter is missing.');
          return;
        }

        await axios.put(`http://localhost:8080/api/studentapp/student/${nim}`, this.student);

        // Assuming a successful update, you can redirect to the student list or show a success message
        this.$router.push({ name: 'student-list' });
      } catch (error) {
        // Handle errors (show error message, log, etc.)
        console.error('Error updating student:', error.response.data);
      }
    },
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
  created() {
    const nim = this.$route.params.id;

    if (nim) {
      this.fetchStudentDetails(nim);
    } else {
      console.error('NIM parameter is missing.');
    }
  },
};
</script>

  
  <style scoped>
    span {
        color: white;
    }
    
  </style>
  