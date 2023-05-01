<template>
<el-container >
    <el-dialog
        title="文件上传"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
            <el-upload
                drag
                action="/file/upload"
                limit="1"
                :on-success="onUploadSuccess"
                multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            </el-upload>
 
        <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
        </span>
    </el-dialog>
    <el-dialog
        title="文件获取"
        :visible.sync="centerDialogVisible2"
        width="30%"
        center>
        <el-from>
            <el-input v-model="link" placeholder="请输入"></el-input>
            <el-button @click="getFile">提 交</el-button>
        </el-from>
        <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible2 = false">取 消</el-button>
            <el-button type="primary" @click="centerDialogVisible2 = false">确 定</el-button>
        </span>
    </el-dialog>
    <el-header style="background-color:cadetblue;line-height: 65px;" height="100px" >文件托管平台</el-header>
    <el-container height="800px">
        <el-aside width="200px" style="background-color: cornflowerblue; overflow-y: auto; min-height: 600px;">
            <div div style="height: 100%; display: flex; flex-direction: column;">
                <nav>
                <li><router-link to="/login">登录</router-link></li>
                <li><router-link to="/">首页</router-link></li>
                <li>
                    <a href="/logout">退出登录</a>
                </li>
                </nav>
            </div>
        </el-aside>

        <el-main>
            <el-header style="display: flex; align-items: center; justify-content: space-between; height: 80px;">
                <div>
                    <el-button type="primary" icon="el-icon-upload" @click="centerDialogVisible = true">上传</el-button>
                    <el-button type="primary" icon="el-icon-folder-add" @click="centerDialogVisible2 = true">获取</el-button>
                </div>
                <el-form style="display: flex;">
                    <el-input v-model="name" placeholder="请输入" width="200px"></el-input>
                    <el-button type="primary" icon="el-icon-search" @click="queryFile">查询</el-button>
                </el-form>
                </el-header>
            <div>
                <el-table :data="pagedFiles">
                <el-table-column width="100px"><i class="el-icon-document"></i></el-table-column>
                <el-table-column prop="name" label="文件名" max-width="25%"></el-table-column>
                <el-table-column prop="create_date" label="修改时间"></el-table-column>
                <el-table-column prop="type" label="文件类型" width="100px"></el-table-column>
                <el-table-column prop="observed_size" label="大小"></el-table-column>
                <el-table-column>
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-share" circle @click="shareFile(scope.row.id)"></el-button>
                        <el-button type="success" icon="el-icon-download" circle @click="downloadFile(scope.row.id)"></el-button>
                        <el-button type="danger" icon="el-icon-delete" slot="reference" circle @click="deleteFile(scope.row.id)"></el-button>
                    </template>
                </el-table-column>
                </el-table>
                <el-pagination
                :current-page="currentPage"
                :page-size="pageSize"
                :total="totalFiles"
                layout="prev, pager, next"              
                @current-change="handlePageChange">
                </el-pagination>
            </div>
        </el-main>

    </el-container>
</el-container>
</template>



<script>
import axios from 'axios';
import { RouterLink } from 'vue-router';
export default {
    components: {
        RouterLink,
    },
    data() {
        return {
            name: '',
            link: '',
            centerDialogVisible: false,
            centerDialogVisible2: false,
            Files: [],
            currentPage: 1,
            pageSize: 6, // 每页显示的数量
        };
    },
    created() {
        axios.get("/file/all").then(res => {
            this.Files = res.data;
        }).catch(error => {
            console.log(error);
        });
    },
    computed: {
        totalFiles() {
            return this.Files.length; // 总数，即数组的长度
        },
        pagedFiles() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.Files.slice(start, end); // 分页后的数组
        },
    },

    methods: {
        onUploadSuccess(response, file, fileList) {
            // 处理上传成功后的逻辑
            location.reload;
            console.log('onUploadSuccess:', response, file, fileList)
        },
        handlePageChange(currentPage) {
            this.currentPage = currentPage; // 更新当前页码
        },
        deleteFile(id){
            axios.delete("/file/delete/"+id).then(res =>{
                let json = res.data;
                if (json.param == 'success') {
                    this.$message.success(json.msg)
                    this.$router.push( '/file' );
                    location.reload();
                } else {
                    this.$message.error(json.msg);
                }
            });
        },
        shareFile(id){
            axios.get("share/create/"+id).then(res=>{
                let json = res.data;
                if (json.param == 'success') {
                    this.$alert(json.msg,"分享链接",{confirmButtonText: '确定'})
                } else {
                    this.$message.error("出现错误");
                }
            })
        },
        downloadFile(id) {
            axios.get(`file/download/${id}`, { responseType: 'blob' })
            .then((response) => {
                // 获取文件名
                const filename = decodeURIComponent(response.headers['content-disposition'].split('=')[1])
                // 创建一个URL来下载文件
                const url = window.URL.createObjectURL(new Blob([response.data]))
                const link = document.createElement('a')
                link.href = url
                link.setAttribute('download', filename) // 设置下载文件名
                document.body.appendChild(link)
                link.click()
                })
        },
        getFile(){
            axios.get('/share/get/'+this.link).then(res=>{
                let json = res.data;
                if (json.param == 'success') {
                    this.$message.success(json.msg);
                    location.reload();
                } else {
                    this.$message.error(json.msg);
                }
                })
        },
        queryFile(){
            axios.get('/file/query/'+this.name).then(res=>{
                if(this.name=='')
                    location.reload
                this.Files = res.data
            })
        }
    },
};
</script>
<style>
li{
    margin: 20px;
}
</style>