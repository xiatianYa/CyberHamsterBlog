import axios from 'axios'
import store from '@/store'

//1:利用axuis方法 create 去创建一个axios实例
const requests =axios.create({
    baseURL:'http://127.0.0.1:8080/api',//基于那个路径
    timeout:5000, 
})
//请求拦截器
requests.interceptors.request.use((config)=>{
    return config
})
//响应拦截器
requests.interceptors.response.use(
    (res)=>{
        return res.data;
    },(err)=>{
        
    },
)
export default requests
