import { createRouter, createWebHistory } from 'vue-router';

import StudentList from './components/StudentList.vue';
import AddStudent from './components/AddStudent.vue';
import DeleteStudent from './components/DeleteStudent.vue'
import UpdateStudent from './components/UpdateStudent.vue';
import StudentDetail from './components/StudentDetail.vue';

const routes = [
    { path: '/', name: 'student-list', component: StudentList },
    { path: '/add-student', name: 'add-student', component: AddStudent },
    { path: '/delete-student/:id', name: 'delete-student', component: DeleteStudent },
    { path: '/update-student/:id', name: 'update-student', component: UpdateStudent },
    { path: '/student-detail/:id', name: 'student-detail', component: StudentDetail },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
