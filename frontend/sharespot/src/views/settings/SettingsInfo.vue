<template>
    <v-list class="mx-2">
        <v-list-item>
        <v-list-item-title class="mx-2 my-4">도움말</v-list-item-title>
        <v-icon
        @click="next"
        >
        mdi-chevron-right
        </v-icon>
        </v-list-item>
        <v-divider></v-divider>
        <v-list-item>
        <v-list-item-title class="mx-2 my-4">이용약관</v-list-item-title>
        <v-icon
        @click="next"
        >
        mdi-chevron-right
        </v-icon>
        </v-list-item>
    </v-list>
</template>

<script>
export default {
    name: 'S07P12A505SettingsNotice',

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