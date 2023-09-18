<template>
  <div class="app-container">
    <div style="margin-bottom: 20px">
      <el-button @click="openTag()">新增标签</el-button>
    </div>
    <el-table v-loading="listLoading" :data="pageInfo.records" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" prop="tagsId" label="ID" width="95">
      </el-table-column>
      <el-table-column label="标签名称" prop="tagsName">
      </el-table-column>
      <el-table-column :filters="[{ text: '未删除', value: 0 }, { text: '已删除', value: 1 }]" :filter-method="filterDelete"
        class-name="status-col" label="删除状态" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isDeleted === 0 ? 'success' : 'warning'">{{ scope.row.isDeleted === 0 ? '未删除' :
            '已删除' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="创建时间" width="150">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="修改" width="110">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" circle @click="openTag(scope.row.tagsId)"></el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="删除" width="110">
        <template slot-scope="scope">
          <el-button :disabled="scope.row.isDeleted === 1" type="danger" icon="el-icon-delete" circle
            @click="removeTag(scope.row.tagsId)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination style="text-align: center; padding-top: 20px;" background @size-change="handleChangeSize" @next-click="handleChangeNext"
      @prev-click="handleChangePrev" @current-change="handleChangeCurrent" :page-size="pageInfo.size" layout="prev, pager, next"
      :total="pageInfo.total" >
    </el-pagination>
    <el-dialog title="标签详情" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="标签名称" :label-width="formLabelWidth">
          <el-input v-model="tagMenu.tagsName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="删除状态" :label-width="formLabelWidth">
          <el-select v-model="tagMenu.isDeleted" placeholder="请选择归档类型">
            <el-option label="未删除" :value=0></el-option>
            <el-option label="已删除" :value=1></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitTagIfy()">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { reqGetTagList, reqGetTagById, reqUpdateTag, reqAddTag, reqDeleteTag } from "@/api/tag"
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
      listLoading: true,
      dialogFormVisible: false,
      tagMenu: {},
      classIfyMenu: {},
      formLabelWidth: '120px'
    }
  },
  created() {
    this.GetTagList()
  },
  methods: {
    //分页
    handleChangeSize(current) {
      console.log(current);
    },
    handleChangeNext(current) {
      this.pageInfo.current = current
      this.GetTagList()
    },
    handleChangePrev(current) {
      this.pageInfo.current = current
      this.GetTagList()
    },
    handleChangeCurrent(current) {
      this.pageInfo.current = current
      this.GetTagList()
    },
    //删除过滤器  
    filterDelete(value, row) {
      return row.isDeleted === value;
    },
    async GetTagList() {
      this.listLoading = true
      const result = await reqGetTagList(this.pageInfo);
      if (result.code === 20000) {
        this.pageInfo = result.data
        this.listLoading = false
      }
    },
    async openTag(Id) {
      this.dialogFormVisible = true
      if (Id === undefined) {
        this.tagMenu = {
          isDeleted: 0
        }
        return false
      }
      const result = await reqGetTagById(Id)
      if (result.code === 20000) {
        this.tagMenu = result.data
      }
    },
    async submitTagIfy() {
      if (this.tagMenu.tagsId === undefined) {
        this.addTag()
      } else {
        this.updateTag()
      }
      this.dialogFormVisible = false
    },
    async addTag() {
      const result = await reqAddTag(this.tagMenu)
      console.log(result);
      if (result.code === 20000) {
        this.$message({
          message: result.msg,
          type: 'success'
        });
      } else {
        this.$message({
          message: result.msg,
          type: 'error'
        });
      }
      this.GetTagList()
    },
    async updateTag() {
      const result = await reqUpdateTag(this.tagMenu)
      if (result.code === 20000) {
        this.$message({
          message: result.msg,
          type: 'success'
        });
      } else {
        this.$message({
          message: result.msg,
          type: 'error'
        });
      }
      this.GetTagList()
    },
    async removeTag(Id) {
      this.$confirm('此操作将删除标签', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const result = await reqDeleteTag(Id)
        if (result.code === 20000) {
          this.$message({
            message: result.msg,
            type: 'success'
          });
        }
        this.GetTagList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: result.msg
        });
        this.GetTagList()
      });
    }
  },
}
</script>

