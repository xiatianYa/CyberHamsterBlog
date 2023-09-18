<template>
  <div class="app-container">
    <div style="margin-bottom: 20px">
      <el-button @click="addEssay()">新增文章</el-button>
      <el-button @click="removeEssay()">删除选中文章</el-button>
      <el-button type="primary" icon="el-icon-search">搜索</el-button>
    </div>
    <el-table ref="multipleTable" v-loading="listLoading" :data="pageInfo.records" element-loading-text="Loading" border fit
      highlight-current-row :default-sort="{ prop: 'date', order: 'ascending' }"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column align="center" prop="blogId" label="ID" width="60">
      </el-table-column>
      <el-table-column label="文章标题" prop="blogTitle" width="250">
      </el-table-column>
      <el-table-column label="文章标签关联" align="center">
        <template slot-scope="scope">
          <el-tag v-for="item in scope.row.tbTagsEntityList" :key="item.label" :type="item.type" effect="plain">
            {{ item.label }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column :filters="classifyList" :filter-method="filterClassify" label="归档" width="100" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.blogClassIfyName }}</span>
        </template>
      </el-table-column>
      <el-table-column :filters="[{ text: '编辑完成', value: 0 }, { text: '编辑中', value: 1 }]" :filter-method="filterStatus"
        class-name="status-col" label="编辑状态" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.blogStatus === 0 ? 'success' : 'warning'">{{ scope.row.blogStatus === 0 ? '编辑完成' :
            '编辑中' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :filters="[{ text: '未删除', value: 0 }, { text: '已删除', value: 1 }]" :filter-method="filterDelete"
        class-name="status-col" label="删除状态" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isDeleted === 0 ? 'success' : 'warning'">{{ scope.row.isDeleted === 0 ? '未删除' :
            '已删除' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column sortable align="center" prop="created_at" label="创建时间" width="110">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column sortable align="center" prop="created_at" label="修改时间" width="130">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.updateTime }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="编辑文章" width="80">
        <template slot-scope="scope">
          <el-button :disabled="scope.row.isDeleted === 1" type="success" icon="el-icon-edit" circle
            @click="editEssay(scope.row.blogId)"></el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="修改" width="70">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" circle @click="openEssay(scope.row.blogId)"></el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="删除" width="70">
        <template slot-scope="scope">
          <el-button :disabled="scope.row.isDeleted === 1" type="danger" icon="el-icon-delete" circle
            @click="deleteEssay(scope.row.blogId)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination style="text-align: center; padding-top: 20px;" @size-change="handleChangeSize" @next-click="handleChangeNext" @prev-click="handleChangePrev" @current-change="handleChangeCurrent" background layout="prev, pager, next" :size="pageInfo.size" :total="pageInfo.total" >
    </el-pagination>
    <el-dialog title="文章详情" :visible.sync="dialogFormVisible">
      <el-form :model="essay">
        <el-form-item label="文章标题" :label-width="formLabelWidth">
          <el-input v-model="essay.blogTitle" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="归档" :label-width="formLabelWidth">
          <el-select v-model="essay.blogClassifyId" placeholder="请选择归档类型">
            <el-option v-for="(item, index) in classifyList" :label="item.text" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标签关联" :label-width="formLabelWidth">
          <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
          <div style="margin: 15px 0;"></div>
          <el-checkbox-group v-model="checkedTags" @change="handleCheckedCitiesChange">
            <el-checkbox v-for="tag in tags" :label="tag.tagsId" :key="tag.tagsName">{{ tag.tagsName }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="编辑状态" :label-width="formLabelWidth">
          <el-select v-model="essay.blogStatus" placeholder="请选择归档类型">
            <el-option label="编辑完成" :value=0></el-option>
            <el-option label="编辑中" :value=1></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="删除状态" :label-width="formLabelWidth">
          <el-select v-model="essay.isDeleted" placeholder="请选择归档类型">
            <el-option label="未删除" :value=0></el-option>
            <el-option label="已删除" :value=1></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitEssay()">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { reqGetEssayListByPage, reqGetEssayById, reqUpdateEssay, reqDeleteEssay, reqAddEssay, reqRemoveEssay } from '@/api/blog'
import { reqGetEssayTagList } from "@/api/tag"
import { reqGetClassIfyListByPage } from "@/api/classify"
export default {
  data() {
    return {
      //分页
      pageInfo:{
        records:[],
        total:0,
        size:15,
        current:1,
        orders:''
      },
      //标签加载
      classifyList: null,
      //文章加载
      listLoading: true,
      //弹出框
      dialogFormVisible: false,
      //文章修改 新增内容
      essay: {
        blogTitle: '',
        blogContent: '',
        blogClassifyId: '',
        blogStatus: '',
        isDeleted: '',
      },
      multipleSelection: [],
      formLabelWidth: '120px',
      //被选择的标签
      checkedTags: [],
      //是否被全选
      checkAll: false,
      //全部标签
      tags: [],
      //全选按钮
      isIndeterminate: false
    }
  },
  created() {
    this.GetClassIfyListByPage()
    this.GetEssayListByPage()
    this.GetTagsList()
  },
  methods: {
    //分页
    handleChangeSize(current){
      console.log(current);
    },
    handleChangeNext(current){
      this.pageInfo.current=current
      this.GetEssayListByPage()
    },
    handleChangePrev(current){
      this.pageInfo.current=current
      this.GetEssayListByPage()
    },
    handleChangeCurrent(current){
      this.pageInfo.current=current
      this.GetEssayListByPage()
    },
    //弹出框选择处理器
    handleCheckAllChange(val) {
      this.checkedTags = val ? this.tags.map(item => {
        return item.tagsId
      }) : [];
      this.isIndeterminate = false;
    },
    //弹出框全选处理器
    handleCheckedCitiesChange(value) {
      //获取选择数量
      let checkedCount = value.length;
      //判断是否全选
      this.checkAll = checkedCount === this.tags.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.tags.length;
    },
    //选择器
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //编辑过滤器
    filterStatus(value, row) {
      return row.blogStatus === value;
    },
    //归档过滤器
    filterClassify(value, row) {
      return row.blogClassifyId === value;
    },
    //删除过滤器
    filterDelete(value, row) {
      return row.isDeleted === value;
    },
    //获取标签列表
    async GetTagsList() {
      const result = await reqGetEssayTagList()
      if (result.code === 20000) {
        result.data.map(item => {
          this.tags.push({ tagsId: item.tagsId, tagsName: item.tagsName })
        })
      }
    },
    //获取文章信息
    async GetEssayListByPage() {
      this.listLoading = true
      const result = await reqGetEssayListByPage(this.pageInfo.current,this.pageInfo.size)
      if (result.code === 20000) {
        //遍历归档列表 获取归档名称
        this.pageInfo=result.data
        this.pageInfo.records = this.pageInfo.records.filter(essay => {
          for (let index = 0; index < this.classifyList.length; index++) {
            const classify = this.classifyList[index];
            if (essay.blogClassifyId === classify.value) {
              essay.blogClassIfyName = classify.text
            }
          }
          //处理关联标签
          return this.handleEssayAndTag(essay)
        })
        this.listLoading = false
      }
    },
    //处理博客文章关联标签
    handleEssayAndTag(essay) {
      //处理关联标签
      const Tagitems = [
        { type: '', },
        { type: 'success', },
        { type: 'info', },
        { type: 'danger', },
        { type: 'warning', },
        { type: '', },
        { type: 'success', },
        { type: 'info', },
        { type: 'danger', },
        { type: 'warning', },
        { type: '', },
        { type: 'success', },
        { type: 'info', },
        { type: 'danger', },
        { type: 'warning', },
      ];
      for (let index = 0; index < essay.tbTagsEntityList.length; index++) {
        const tag = essay.tbTagsEntityList[index];
        Tagitems[index].label = tag.tagsName
        Tagitems[index].tagsId = tag.tagsId
      }
      essay.tbTagsEntityList = Tagitems.filter(item => item.label !== undefined)
      return essay
    },
    //获取归档信息
    async GetClassIfyListByPage() {
      this.classifyList=[]
      const result = await reqGetClassIfyListByPage()
      if (result.code === 20000) {
        this.classifyList = result.data.map(item => {
          if (item.isDeleted === 1) {
            return;
          }
          return { text: item.classifyName, value: item.classifyId }
        }).filter(item2 => item2 !== undefined);
      }
    },
    //根据文章ID获取文章信息
    async openEssay(blogId) {
      this.dialogFormVisible = true
      const result = await reqGetEssayById(blogId)
      if (result.code === 20000) {
        this.essay = this.handleEssayAndTag(result.data)
        this.checkedTags = this.essay.tbTagsEntityList.map(item => {
          return item.tagsId
        })
      }
    },
    //提交文章修改信息
    async submitEssay() {
      if (this.essay.blogId === undefined) {
        this.essay.tbTagsEntityList = this.checkedTags.map(item => {
          return { tagsId: item }
        })
        const result = await reqAddEssay(this.essay)
        if (result.code === 20000) {
          this.$message({
            message: result.msg,
            type: 'success'
          });
        }
      } else {
        this.essay.tbTagsEntityList = this.checkedTags.map(item => {
          return { tagsId: item }
        })
        const result = await reqUpdateEssay(this.essay)
        if (result.code === 20000) {
          this.$message({
            message: result.msg,
            type: 'success'
          });
        }
      }
      this.GetEssayListByPage()
      this.dialogFormVisible = false
    },
    //删除文章
    deleteEssay(blogId) {
      this.$confirm('此操作将删除文章', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const result = await reqDeleteEssay(blogId)
        if (result.code === 20000) {
          this.$message({
            message: result.msg,
            type: 'success'
          });
        }
        this.GetEssayListByPage()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: result.msg
        });
        this.GetEssayListByPage()
      });
    },
    //新增文章
    addEssay() {
      //清空文章对象
      this.essay = {
        blogStatus: 1,
        blogContent: '',
        isDeleted: 0
      }
      //清除添加全选
      this.checkAll = false
      //清除标签列表
      this.checkedTags = []
      //打开拟态窗
      this.dialogFormVisible = true
    },
    //删除选中文章
    async removeEssay() {
      const Ids = this.multipleSelection.map(item => {
        return item.blogId
      })
      if (Ids.length === 0) {
        this.$message({
          type: 'info',
          message: '请先选择删除文章'
        });
        return
      }
      this.$confirm('此操作将删除文章', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const result = await reqRemoveEssay(Ids)
        if (result.code === 20000) {
          this.$message({
            message: '删除成功',
            type: 'success'
          });
        }
        this.GetEssayListByPage()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
        this.GetEssayListByPage()
      });
    },
    //跳转到文章编辑页面
    editEssay(blogId) {
      this.$router.push({ path: '/blog/editessay', query: { blogId: blogId } })
    }
  }
}
</script>
