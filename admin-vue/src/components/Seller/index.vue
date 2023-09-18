<template>
  <div class="Content">
    <div class="content-1">
      <div class="meun-1">
        <span class="math">{{ count.blogCount }}</span><br /><br />
        <span class="text">文章</span>
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-biji"></use>
        </svg>
      </div>
      <div class="meun-2">
        <span class="math">{{ count.classifyCount }}</span><br /><br />
        <span class="text">标签</span>
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-biaoqian"></use>
        </svg>
      </div>
      <div class="meun-3">
        <span class="math">{{ count.tagsCount }}</span><br /><br />
        <span class="text">分类</span>
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-fenlei"></use>
        </svg>
      </div>
      <div class="meun-4">
        <span class="math">{{ count.messageCount }}</span><br /><br />
        <span class="text">留言数</span>
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-xiaoxi"></use>
        </svg>
      </div>
    </div>
    <div style="display: flex; overflow: hidden; justify-content: space-between;">
      <div>
        <div class="SellerTop">
          <span class="text-S">分类文章数统计</span>
        </div>
        <div class="line-Seller"></div>
        <div class="SellerOption" style="" ref="seller_ref"></div>
      </div>

      <div>
        <div class="SellerTop">
          <span class="text-S">文章访问TOP.10</span>
        </div>
        <div class="line-Seller"></div>
        <div class="SellerOption">
          <el-table :data="BlogAccessList" style="width: 100%" height="100%">
            <el-table-column prop="blogId" label="文章ID" width="70">
            </el-table-column>
            <el-table-column prop="blogTitle" label="文章标题">
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" width="95">
            </el-table-column>
          </el-table>
        </div>
      </div>

      <div style=" overflow: hidden;margin-right: 10px;">

        <div class="SellerTop" style="width: 514px; ">
          <span class="text-S">文章访问统计图</span>
        </div>
        <div class="line-Seller" style="width: 460px;"></div>
        <div class="Seller_Option" ref="Seller_Option"></div>
      </div>
    </div>

    <div style="display: flex; justify-content: space-between;">
      <div>
        <div class="SellerTop" style="width: 514px; margin-top: 20px;">
          <span class="text-S">近期文章</span>
        </div>
        <div class="line-Seller" style="width: 460px;"></div>
        <div class="Table" style="margin-bottom: 20px;">
          <el-table :data="RecentBlogList" style="width: 100%" height="100%">
            <el-table-column prop="blogId" label="文章ID" width="180">
            </el-table-column>
            <el-table-column prop="blogTitle" label="文章标题" width="180">
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间">
            </el-table-column>
          </el-table>
        </div>
      </div>

      <div>
        <div class="SellerTop" style="width: 514px; margin-top: 20px;">
          <span class="text-S">近期留言</span>
        </div>
        <div class="line-Seller" style="width: 460px;"></div>
        <div class="Table" style="margin-bottom: 20px;">
          <el-table :data="messageList" style="width: 100%" height="100%">
            <el-table-column prop="messageId" label="留言ID" width="180">
            </el-table-column>
            <el-table-column prop="messageName" label="留言姓名" width="180">
            </el-table-column>
            <el-table-column prop="messageContent" label="留言内容" width="180">
            </el-table-column>
            <el-table-column prop="messageLike" label="点赞数" width="180">
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间">
            </el-table-column>
          </el-table>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { reqGetBlogCount, reqGetClassIfyCount, reqGetTagsCount, getMessageCount, getClassIfyAndBlogCount, getRecentBlogList, getMessageList, getAccessBlogList, getBlogCountByYear } from "@/api/tongji"
export default {
  name: 'Seller',
  data() {
    return {
      count: {},
      chartsInstans: null,
      RecentBlogList: [],
      messageList: [],
      BlogAccessList: [],
      CountByYear: {}
    }
  },
  mounted() {
    this.initChatrs()
  },
  methods: {
    // 初始化对象
    async initChatrs() {
      this.chartsInstans = this.$echarts.init(this.$refs.seller_ref)
      const blogCountResult = await reqGetBlogCount();
      const blogClassIfyResult = await reqGetClassIfyCount();
      const blogTagsResult = await reqGetTagsCount();
      const blogMessageResult = await getMessageCount();
      const blogClassIfyAndBlogCount = await getClassIfyAndBlogCount();
      const blogRecentBlogList = await getRecentBlogList();
      if (blogCountResult.code === 20000 && blogClassIfyResult.code === 20000 && blogTagsResult.code === 20000 && blogMessageResult.code === 20000) {
        this.count = {
          blogCount: blogCountResult.data,
          classifyCount: blogClassIfyResult.data,
          tagsCount: blogTagsResult.data,
          messageCount: blogMessageResult.data
        }
      } else {
        this.$message({
          message: "文章数据数据获取失败!",
          type: 'error'
        });
      }
      if (blogRecentBlogList.code === 20000) {
        this.RecentBlogList = blogRecentBlogList.data;

      } else {
        this.$message({
          message: "近期文章获取失败!",
          type: 'error'
        });
      }
      const blogMessageList = await getMessageList();
      if (blogMessageList.code === 20000) {
        this.messageList = blogMessageList.data.records;

      } else {
        this.$message({
          message: "近期留言获取失败!",
          type: 'error'
        });
      }
      const blogAccessList = await getAccessBlogList();
      if (blogAccessList.code === 20000) {
        this.BlogAccessList = blogAccessList.data;

      } else {
        this.$message({
          message: "文章访问Top获取失败!",
          type: 'error'
        });
      }
      const blogCountByYear = await getBlogCountByYear();
      if (blogCountByYear.code === 20000) {
        this.CountByYear = blogCountByYear.data;
      } else {
        this.$message({
          message: "文章访问统计图获取失败!",
          type: 'error'
        });
      }
      const option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          bottom: 'bottom'
        },
        series: [
          {
            name: '文章统计',
            type: 'pie',
            radius: '50%',
            data: blogClassIfyAndBlogCount.data,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      this.chartsInstans.setOption(option)
      //echars 初始化
      this.initSellerOption()
    },
    initSellerOption() {
      this.SellerOption = this.$echarts.init(this.$refs.Seller_Option)
      const optionSeller = {
        title: {
          text: '月份'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {},
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
          boundaryGap: [0, 0.01]
        },
        yAxis: {
          type: 'category',
          data: this.CountByYear.yaxisData
        },
        series: [
          this.CountByYear.series
        ]
      }
      this.SellerOption.setOption(optionSeller)
    }
  }

}
</script>

<style lang="less" scoped>
.icon {
  position: absolute;
  font-size: 50px;
  right: 15px;
  bottom: 15px;
}

.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}

.Content {
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: #f7f7f7;
  border: 1px solid #ccc;

  .content-1 {
    margin-top: 20px;
    display: flex;
    width: 100%;
    height: 80px;
    margin-bottom: 30px;

    .meun-1 {
      min-width: 125px;
      position: relative;
      border-radius: 5%;
      margin: 10px 10px;
      flex: 1;
      width: 20%;
      height: 100%;
      border: 1px solid #ccc;
      background-color: white;
    }

    .meun-2 {
      min-width: 125px;
      position: relative;
      border-radius: 5%;
      border: 1px solid #ccc;
      margin: 10px 10px;
      flex: 1;
      margin-right: 10px;
      width: 20%;
      height: 100%;
      background-color: white;
    }

    .meun-3 {
      min-width: 125px;
      position: relative;
      border-radius: 5%;
      border: 1px solid #ccc;
      margin: 10px 10px;
      flex: 1;
      margin-right: 10px;
      width: 20%;
      height: 100%;
      background-color: white;



    }

    .meun-4 {
      min-width: 125px;
      position: relative;
      border-radius: 5%;
      border: 1px solid #ccc;
      margin: 10px 10px;
      flex: 1;
      width: 20%;
      height: 100%;
      background-color: white;

    }

    .text {
      padding: 7px 3px 3px 5px;
      font-size: 14px;
      color: #ccc;
    }

    .math {
      padding-left: 7px;
      font-weight: bold;
      font-size: 24px;
      color: #969696;
    }

  }

  .SellerTop {
    margin-left: 10px;
    width: 400px;
    height: 50px;
    border: 1px solid #ccc;
    border-bottom: none;
    background-color: white;

    .text-S {
      color: #ccc;
      font-size: 20px;
      padding-left: 10px;
      line-height: 50px;
    }
  }

  .line-Seller {
    width: 360px;
    height: 2px;
    background-color: #ccc;
    margin-left: 30px;
  }

  .SellerOption {
    border: 1px solid #ccc;
    margin-left: 10px;
    background-color: white;
    width: 400px;
    height: 350px;
    border-top: none;
  }

  .Seller_Option {

    border: 1px solid #ccc;
    margin-left: 10px;
    background-color: white;
    width: 514px;
    height: 350px;
    border-top: none;
  }

  .Table {
    border: 1px solid #ccc;
    margin-left: 10px;
    background-color: white;
    width: 514px;
    height: 350px;
    border-top: none;
  }

}
</style>
