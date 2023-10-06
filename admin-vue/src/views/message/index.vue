<template>
  <div class="app-container">
    <div style="margin-bottom: 20px">
      <el-button @click="DeleteMessageReport()">删除恶意留言</el-button>
      <el-button @click="DeleteMessageReportByYes()">移除友善留言</el-button>
    </div>
    <!-- 表格区域 -->
    <el-table v-loading="Loading" :data="pageInfo.records" element-loading-text="Loading" border fit highlight-current-row
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column align="center" prop="reportId" label="ID">
      </el-table-column>
      <el-table-column align="center" prop="reportContent" label="举报内容">
      </el-table-column>
      <el-table-column sortable align="center" prop="created_at" label="创建时间">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="移除" width="110">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-delete" circle
            @click="DeleteMessageReportByYesById(scope.row.reportId)"></el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="删除" width="110">
        <template slot-scope="scope">
          <el-button type="danger" icon="el-icon-delete" circle
            @click="deleteMessageReportById(scope.row.reportId)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination style="text-align: center; padding-top: 20px;" background @size-change="handleChangeSize"
      @next-click="handleChangeNext" @prev-click="handleChangePrev" @current-change="handleChangeCurrent"
      :page-size="pageInfo.size" layout="prev, pager, next" :total="pageInfo.total">
    </el-pagination>
  </div>
</template>

<script>
import { reqGetMessageReportList, reqDeleteMessageReport, reqYesMessageReport } from "@/api/message"
export default {
  data() {
    return {
      //分页
      pageInfo: {
        records: [],
        total: 0,
        size: 15,
        current: 1,
        orders: ''
      },
      Loading: true,
      multipleSelection: []
    }
  },
  created() {
    this.GetMessageReportList()
  },
  methods: {
    //分页
    handleChangeSize(current) {
      console.log(current);
    },
    handleChangeNext(current) {
      this.pageInfo.current = current
      this.GetMessageReportList()
    },
    handleChangePrev(current) {
      this.pageInfo.current = current
      this.GetMessageReportList()
    },
    handleChangeCurrent(current) {
      this.pageInfo.current = current
      this.GetMessageReportList()
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    async GetMessageReportList() {
      const result = await reqGetMessageReportList(this.pageInfo)
      if (result.code === 20000) {
        this.pageInfo = result.data
        this.Loading = false;
      }
    },
    async DeleteMessageReport() {
      const Ids = this.multipleSelection.map(item => {
        return item.reportId
      })
      this.$confirm('此操作将移除恶意留言', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const result = await reqDeleteMessageReport(Ids)
        if (result.code === 20000) {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
        }
        this.GetMessageReportList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
        this.GetMessageReportList()
      })
    },
    async deleteMessageReportById(Id) {
      const Ids = [Id]
      this.$confirm('此操作将删除文章', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const result = await reqDeleteMessageReport(Ids)
        if (result.code === 20000) {
          this.$message({
            message: '删除成功',
            type: 'success'
          });
        }
        this.GetMessageReportList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
        this.GetMessageReportList()
      });
    },
    async DeleteMessageReportByYes() {
      const Ids = this.multipleSelection.map(item => {
        return item.reportId
      })
      this.$confirm('此操作将移除友善留言', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const result = await reqYesMessageReport(Ids)
        if (result.code === 20000) {
          this.$message({
            message: '移除成功',
            type: 'success'
          });
        }
        this.GetMessageReportList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消移除'
        });
        this.GetMessageReportList()
      });
    },
    async DeleteMessageReportByYesById(Id) {
      const Ids = [Id]
      this.$confirm('此操作将移除友善留言', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const result = await reqYesMessageReport(Ids)
        if (result.code === 20000) {
          this.$message({
            message: '移除成功',
            type: 'success'
          });
        }
        this.GetMessageReportList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消移除'
        });
        this.GetMessageReportList()
      });
    }
  }
}
</script>

