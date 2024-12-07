// 创建一个 Axios 实例
const instance = axios.create({
  baseURL: 'http://localhost/', // 你的API基础URL
  timeout: 10000, // 请求超时时间
});

// 添加请求拦截器
instance.interceptors.request.use(
  config => {
    // 在发送请求之前做些什么
    console.log('Request was sent.');
    // 从 localStorage 中获取令牌
    const token = localStorage.getItem('authToken');

    // 如果存在令牌，则将其添加到请求头中
    if (token) {
       config.headers['Authorization'] = token;
    }
    return config;
  },
  error => {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);

