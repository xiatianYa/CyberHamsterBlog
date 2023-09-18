<template>
  <div class="app-container">

    <div style="margin-bottom: 20px">

      <el-button @click="openFriend()">新增友链</el-button>
    </div>
    <!-- 表格区域 -->
    <el-table v-loading="friendLoading" :data="friendList" element-loading-text="Loading" border fit
      highlight-current-row>
      <el-table-column align="center" prop="friendId" label="ID" width="95">
      </el-table-column>
      <el-table-column label="姓名" prop="friendName">
      </el-table-column>
      <el-table-column label="网址" prop="friendAddress">
      </el-table-column>
      <el-table-column label="照片" prop="friendImage">
        <template slot-scope="scope">
          <el-avatar :size="50" :src="scope.row.friendImage"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column label="签名" prop="friendSignature">
      </el-table-column>
      <el-table-column :filters="[{ text: '未删除', value: 0 }, { text: '已删除', value: 1 }]" :filter-method="filterDelete"
        class-name="status-col" label="删除状态" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isDeleted === 0 ? 'success' : 'warning'">{{ scope.row.isDeleted === 0 ? '未删除' :
            '已删除' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="建立时间" width="150">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <!-- 修改模块 -->
      <el-table-column align="center" prop="created_at" label="修改" width="110">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" circle @click="openFriend(scope.row.friendId)"></el-button>
        </template>
      </el-table-column>
      <!-- 删除模块 -->
      <el-table-column align="center" prop="created_at" label="删除" width="110">
        <template slot-scope="scope">
          <el-button :disabled="scope.row.isDeleted === 1" type="danger" icon="el-icon-delete" circle
            @click="removeFriend(scope.row.friendId)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改对话框 -->
    <el-dialog title="友链详情" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="friendMenu.friendName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="网址" :label-width="formLabelWidth">
          <el-input v-model="friendMenu.friendAddress" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户头像" :label-width="formLabelWidth">
          <el-upload class="avatar-uploader" action="/api/fileoss/uploadOssFile"
            :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="签名" :label-width="formLabelWidth">
          <el-input v-model="friendMenu.friendSignature" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="删除状态" :label-width="formLabelWidth">
          <el-select v-model="friendMenu.isDeleted" placeholder="请选择归档类型">
            <el-option label="未删除" :value=0></el-option>
            <el-option label="已删除" :value=1></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitFriendIfy()">提 交</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { reqGetFriendList, reqGetFriendById, reqUpdateFriend, reqAddFriend, reqDeleteFriend } from "@/api/friend"
export default {
  data() {
    return {
      friendLoading: true,
      dialogFormVisible: false,
      friendList: [],
      friendMenu: {},
      classIfyMenu: {},
      formLabelWidth: '120px',
      imageUrl: ''
    }
  },
  created() {
    this.GetFriendList()
  },
  methods: {
    //上传用户图片
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      this.friendMenu.friendImage = res.data
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
    async GetFriendList() {
      this.friendLoading = true
      const result = await reqGetFriendList();
      if (result.code === 20000) {
        this.friendList = result.data
        this.friendLoading = false
      }
    },
    async openFriend(Id) {
      this.dialogFormVisible = true
      if (Id === undefined) {
        this.friendMenu = {
          isDeleted: 0
        }
        return false
      }
      const result = await reqGetFriendById(Id)
      if (result.code === 20000) {
        this.friendMenu = result.data
      }
    },
    async submitFriendIfy() {
      if (this.friendMenu.friendId === undefined) {
        this.addFriend()
      } else {
        this.updateFriend()
      }
      this.imageUrl=""
      this.dialogFormVisible = false
    },
    async addFriend() {
      const result = await reqAddFriend(this.friendMenu)
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
      this.GetFriendList()
    },
    async updateFriend() {
      const result = await reqUpdateFriend(this.friendMenu)
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
      this.GetFriendList()
    },
    async removeFriend(Id) {
      this.$confirm('此操作将删除友链', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const result = await reqDeleteFriend(Id)
        if (result.code === 20000) {
          this.$message({
            message: result.msg,
            type: 'success'
          });
        }
        this.GetFriendList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: result.msg
        });
        this.GetFriendList()
      });
    }
  },
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
