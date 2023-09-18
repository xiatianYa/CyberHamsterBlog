<template>
  <div class="app-container">
    <div style="margin-bottom: 20px">
      <el-button @click="openClassIfy()">新增归档</el-button>
    </div>
    <el-table v-loading="listLoading" :data="classIfy" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" prop="classifyId" label="ID" width="95">
      </el-table-column>
      <el-table-column label="归档名称" prop="classifyName">
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
      <el-table-column align="center" prop="created_at" label="修改时间" width="150">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.updateTime }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="修改" width="110">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" circle @click="openClassIfy(scope.row.classifyId)"></el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="删除" width="110">
        <template slot-scope="scope">
          <el-button :disabled="scope.row.isDeleted === 1" type="danger" icon="el-icon-delete" circle
            @click="deleteClassIfy(scope.row.classifyId)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="归档详情" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="归档名称" :label-width="formLabelWidth">
          <el-input v-model="classIfyMenu.classifyName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="删除状态" :label-width="formLabelWidth">
          <el-select v-model="classIfyMenu.isDeleted" placeholder="请选择归档类型">
            <el-option label="未删除" :value=0></el-option>
            <el-option label="已删除" :value=1></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitClassIfy()">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { reqGetClassIfyListByPage, reqGetClassIfyById, reqUpdateClassIfy, reqAddClassIfy, reqDeleteClassIfy } from "@/api/classify"
export default {
  data() {
    return {
      listLoading: true,
      dialogFormVisible: false,
      classIfy: [],
      classIfyMenu: {},
      formLabelWidth: '120px'
    }
  },
  created() {
    this.GetClassIfyListByPage()
  },
  methods: {
    //删除过滤器  
    filterDelete(value, row) {
      return row.isDeleted === value;
    },
    async GetClassIfyListByPage() {
      this.listLoading = true
      const result = await reqGetClassIfyListByPage();
      if (result.code === 20000) {
        this.classIfy = result.data
        this.listLoading = false
      }
    },
    async openClassIfy(Id) {
      this.dialogFormVisible = true
      if (Id === undefined) {
        return false
      }
      const result = await reqGetClassIfyById(Id)
      if (result.code === 20000) {
        this.classIfyMenu = result.data
      }
    },
    async submitClassIfy() {
      if (this.classIfyMenu.classifyId === undefined) {
        this.addClassIfy()
      } else {
        this.updateClassIfy()
      }
      this.dialogFormVisible = false
    },
    async addClassIfy() {
      const result = await reqAddClassIfy(this.classIfyMenu)
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
      this.GetClassIfyListByPage()
    },
    async updateClassIfy() {
      const result = await reqUpdateClassIfy(this.classIfyMenu)
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
      this.GetClassIfyListByPage()
    },
    async deleteClassIfy(Id) {
      this.$confirm('此操作将删除归档', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const result = await reqDeleteClassIfy(Id)
        if (result.code === 20000) {
          this.$message({
            message: result.msg,
            type: 'success'
          });
        }
        this.GetClassIfyListByPage()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: result.msg
        });
        this.GetClassIfyListByPage()
      });
    }
  },
}
</script>

