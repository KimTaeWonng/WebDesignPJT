<template>
    <v-container>
        <back-menu title="이용약관"></back-menu>


<div>
<form method="post" action="`${/api/file/upload}`" enctype="multipart/form-data">
    <input type="file" name="files" multiple="multiple">
    <input type="submit">
</form>

<form method="get" action="/api/file">
    파일경로:<input type="text" name="imagePath">
    <input type="submit" value="조회">
</form>
</div>

    </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";
// import {https2} from "@/js/http.js";

export default {
    name: 'S07P12A505SettingsService',
    components: { BackMenu },
    data() {
        return {
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style scoped>

</style>