<template>
    <div class="slide-container">
   <div class="slide-window">
      <div class="slide" 
           :class="{ 'slide-active': transitionOn }" 
           :style="{transform: 'translate3d(' + slideCoord + 'px, 0, 0)',}">
        <SlideCard v-for="card in cardArray" :key="card.name" /></div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'SharespotCarouselMenu',

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