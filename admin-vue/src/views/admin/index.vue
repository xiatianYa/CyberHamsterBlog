<template>
  <div class="app-container">

    <div style="margin-bottom: 20px">

      <el-button @click="openAddUser()">新增用户</el-button>
    </div>
    <!-- 表格区域 -->
    <el-table v-loading="Loading" :data="userList" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" prop="adminUserId" label="ID" width="120">
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
      <!-- 修改模块 -->
      <el-table-column align="center" prop="created_at" label="修改信息" width="100">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" circle @click="openUpdateUser(scope.row.adminUserId)"></el-button>
        </template>
      </el-table-column>
      <!-- 修改密码模块 -->
      <el-table-column align="center" prop="created_at" label="修改密码" width="100">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" circle
            @click="OpenupdateUserPassWord(scope.row.adminUserId)"></el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="删除用户" width="100">
        <template slot-scope="scope">
          <el-button type="danger" icon="el-icon-delete" circle
                     @click="deleteUser(scope.row.adminUserId)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改用户对话框 -->
    <el-dialog title="用户详情" :visible.sync="dialogFormUser">
      <el-form>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="userMenu.loginUserName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户头像" :label-width="formLabelWidth">
          <el-upload class="avatar-uploader" action="http://47.115.213.84:8080/api/fileoss/uploadOssFile" :show-file-list="false"
            :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUpdateUser()">提 交</el-button>
      </div>
    </el-dialog>
    <!-- 新增用户对话框 -->
    <el-dialog title="用户详情" :visible.sync="dialogFormAddUser">
      <el-form>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="userMenu.loginUserName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="userMenu.loginPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户头像" :label-width="formLabelWidth">
          <el-upload class="avatar-uploader"  action="http://47.115.213.84:8080/api/fileoss/uploadOssFile" :show-file-list="false"
            :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitAddUser()">提 交</el-button>
      </div>
    </el-dialog>
    <!-- 修改用户密码对话框 -->
    <el-dialog title="用户详情" :visible.sync="dialogFormPassWord">
      <el-form>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="userMenu.loginPassword" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitupdateUserPassWord()">提 交</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { reqGetUserList, reqGetUserById, reqUpdateUser,reqAddUser,reqUpdateUserPwd,reqDeleteUser } from "@/api/admin"
import { reqUploadFile } from "@/api/common"
import {reqDeleteEssay} from "@/api/blog";
export default {
  data() {
    return {
      Loading: true,
      dialogFormUser: false,
      dialogFormAddUser: false,
      dialogFormPassWord: false,
      userList: [],
      userMenu: {},
      classIfyMenu: {},
      formLabelWidth: '120px',
      imageUrl: '',
    }
  },
  created() {
    this.GetUserList()
  },
  methods: {
    //上传用户图片
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      this.userMenu.avatar = res.data
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 5;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
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
    openAddUser() {
      this.imageUrl=""
      this.userMenu = {}
      this.dialogFormAddUser = true
    },
    async submitAddUser() {
      const result = await reqAddUser(this.userMenu)
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
      this.dialogFormAddUser = false
      this.GetUserList()
    },
    async openUpdateUser(Id) {
      this.imageUrl=""
      const result = await reqGetUserById(Id)
      if (result.code === 20000) {
        this.userMenu = result.data
      }
      this.dialogFormUser = true
    },
    async submitUpdateUser() {
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
        })
      }
      this.dialogFormUser=false;
      this.GetUserList()
    },
    async OpenupdateUserPassWord(Id){
      const result=await reqGetUserById(Id)
      if (result.code === 20000) {
        this.userMenu = result.data
        this.userMenu.loginPassword=""
      }
      this.dialogFormPassWord = true
    },
    async submitupdateUserPassWord(){
      const result = await reqUpdateUserPwd(this.userMenu)
      if (result.code === 20000) {
        this.$message({
          message: result.msg,
          type: 'success'
        });
      } else {
        this.$message({
          message: result.msg,
          type: 'error'
        })
      }
      this.dialogFormPassWord=false;
      this.GetUserList()
    },
    async deleteUser(Id){
      this.$confirm('此操作将删除用户', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const result = await reqDeleteUser(Id)
        if (result.code === 20000) {
          this.$message({
            message: result.msg,
            type: 'success'
          });
        }
        this.GetUserList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: result.msg
        });
        this.GetUserList()
      });
    }
  }
}
</script>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

