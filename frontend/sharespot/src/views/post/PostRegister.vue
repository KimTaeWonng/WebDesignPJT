<template>
  <div>
    <post-input-item></post-input-item>
  </div>
</template>

<script>
import PostInputItem from "@/components/post/PostInputItem.vue";
export default {
  name: "PostRegister",
  components: { PostInputItem },
  props: {
    type: String,
  },
  data() {
    return {};
  },

  mounted() {},

  methods: {},
};
</script>

<style scoped></style>
