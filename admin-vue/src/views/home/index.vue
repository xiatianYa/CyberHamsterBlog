<template>
  <div class="dashboard-container">
		<Seller></Seller>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Seller from '@/components/Seller'
export default {
  name: '博客后台管理系统',
	components:{
		Seller
	},
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }

  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
