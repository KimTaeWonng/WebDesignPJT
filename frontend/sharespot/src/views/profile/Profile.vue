<template>
  <div>
    <profile-info></profile-info>
    <profile-badge></profile-badge>
    <profile-content></profile-content>
  </div>
</template>

<script>
import ProfileInfo from "@/components/profile/ProfileInfo.vue";
import ProfileContent from "@/components/profile/ProfileContent.vue";
import ProfileBadge from "@/components/profile/ProfileBadge.vue";

export default {
  name: "ProfileView",
  components: { ProfileInfo, ProfileContent, ProfileBadge },
  data() {
    return {};
  },

  mounted() {},

  methods: {},
};
</script>

<style scoped></style>
