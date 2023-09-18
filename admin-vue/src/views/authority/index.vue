<template>
  <div class="app-container">
    <!-- 表格区域 -->
    <el-table v-loading="Loading" :data="userList" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" prop="adminUserId" label="ID" width="95">
      </el-table-column>
      <el-table-column label="用户名" prop="loginUserName">
      </el-table-column>
      <el-table-column label="用户头像">
        <template slot-scope="scope">
          <el-avatar :size="50" :src="scope.row.avatar"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column :filters="[{ text: '未锁定', value: 0 }, { text: '已锁定', value: 1 }]" :filter-method="filterDelete"
        class-name="status-col" label="锁定状态" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.locked === 0 ? 'success' : 'warning'">{{ scope.row.locked === 0 ? '未锁定' :
            '已锁定' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :filters="[{ text: '普通人员', value: 0 }, { text: '开发人员', value: 1 }, { text: '管理人员', value: 1 }]"
        :filter-method="filterDelete" class-name="status-col" label="人员类型" width="110" align="center">
        <template slot-scope="scope">
          <el-tag type="success">{{ scope.row.level === 0 ? '普通人员' : scope.row.level === 1 ? '开发人员' : '管理人员' }}</el-tag>
        </template>
      </el-table-column>
      <!-- 修改模块 -->
      <el-table-column align="center" prop="created_at" label="修改" width="110">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" circle @click="openUser(scope.row.adminUserId)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改对话框 -->
    <el-dialog title="用户详情" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="用户级别" :label-width="formLabelWidth">
          <el-select v-model="userMenu.locked" placeholder="请选择归档类型">
            <el-option v-for="(item, index) in lockedList" :label="item.text" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户级别" :label-width="formLabelWidth">
          <el-select v-model="userMenu.level" placeholder="请选择归档类型">
            <el-option v-for="(item, index) in LevelList" :label="item.text" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUser()">提 交</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { reqGetUserList, reqGetUserById, reqUpdateUser } from "@/api/authority"
export default {
  data() {
    return {
      Loading: true,
      dialogFormVisible: false,
      userList: [],
      userMenu: {},
      LevelList: [{
        text: '普通人员',
        value: 0
      }, {
        text: '开发人员',
        value: 1
      },
      {
        text: '管理人员',
        value: 2
      }],
      lockedList:[
        {
          text:'未锁定',
          value:0
        },
        {
          text:'已锁定',
          value:1
        },
      ],
      classIfyMenu: {},
      formLabelWidth: '120px',
      imageUrl: ''
    }
  },
  created() {
    this.GetUserList()
  },
  methods: {
    //删除过滤器  
    filterDelete(value, row) {
      return row.isDeleted === value;
    },
    async GetUserList() {
      this.Loading = true
      const result = await reqGetUserList();
      if (result.code === 20000) {
        this.userList = result.data
        this.Loading = false
      }
    },
    async openUser(Id) {
      this.dialogFormVisible = true
      const result = await reqGetUserById(Id)
      if (result.code === 20000) {
        this.userMenu = result.data
      }
    },
    async submitUser() {
      this.updateUser()
      this.dialogFormVisible = false
    },
    async updateUser() {
      const result = await reqUpdateUser(this.userMenu)
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
      this.GetUserList()
    },
  },
}
</script>

