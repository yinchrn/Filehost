<template>
  
    <div class="login" >
      <el-form :rules="loginRules" :model="login">
        <h2 class="title">用户注册</h2>
        <el-form-item label="用户名" prop="username">
        <el-input v-model="login.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
        <el-input v-model="login.password" placeholder="请输入密码" type="password"></el-input>
        </el-form-item>
        <button class="button" @click.prevent="onSubmit">注册</button>
      </el-form> 
    </div>
  </template>
    
    <script>
   
    import axios from 'axios';
    export default {
      data() {
        return {
          msg:'',
          login: {
            username: '',
            password: '',
          },
          loginRules: {
          username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
          password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        },
        };
      },
      methods: {
        onSubmit() {
          axios({
            method: 'post',
            url: '/User/reg',
            data:{
              'username': this.login.username,
              'password': this.login.password,
            },  
          }).then((response) => {
            if (response.status == 200) {
              let json = response.data;
              if (json.param == 'success') {
                this.$message.success(json.msg)
                this.$router.push( '/' );
              } else {
                  this.$message.error(json.msg)
              }
            } else {
              this.$message.warning('找不到服务器⊙﹏⊙∥!')
            }
          });
        },
      },
    };
    </script>
    
  
  <style>
  .title{
    text-align: center;
    letter-spacing: 0.4em;
  }
  .login {
  
      text-align: left;
      background-color: white;
      width: 350px;
      margin: 100px auto 0;
      padding: 40px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
  }
  
  
  
  .button {
      display: block;
      width: 100%;
      height: 50px;
      margin-top: 20px;
      padding: 5px 10px;
      font-size: 20px;
      font-weight: bold;
      color: white;
      background-color: #333;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.2s ease-in-out;
  }
  
  
  .button:hover {
      background-color: #555;
  }
  
  
  
  
  </style>