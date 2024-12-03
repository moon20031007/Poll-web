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

// 添加响应拦截器
instance.interceptors.response.use(
  response => {
    // 对响应数据做点什么
    console.log('Response received.');
      // 从 localStorage 中获取令牌
      const token = localStorage.getItem('authToken');

      // 如果存在令牌，则将其添加到请求头中
      if (token) {
          config.headers['Authorization'] = `Bearer ${token}`;
      }
    return response;
  },
  error => {
    // 对响应错误做点什么
    if (error.response) {
      // 请求已发出，但服务器响应的状态码不在2xx范围内
      console.error('Error:', error.response.data);
    } else {
      // 发生了某些问题导致请求未发出
      console.error('Network Error:', error.message);
    }
    return Promise.reject(error);
  }
);
