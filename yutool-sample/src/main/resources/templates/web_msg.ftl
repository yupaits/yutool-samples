<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Web网页消息测试页</title>
  <script src="https://cdn.jsdelivr.net/npm/vue"></script>
  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
  <script src="https://unpkg.com/dayjs"></script>
</head>
<body>
<div id="app">
  <div class="main">
    <button class="btn" @click="sendWebMsg">点击测试 {{sendResult.message}}</button>
  </div>
  <hr>
  <div class="msg-container">
    <div v-for="(message, index) in messages" :key="index">
      <p>{{index + 1}}. 【{{message.action}}】{{message.title}} <span class="time">{{dayjs(message.timeStamp).format('YYYY-MM-DD HH:mm:ss')}}</span></p>
      <p>{{message.content}}</p>
    </div>
  </div>
</div>

<style>
.main {
  display: flex;
  margin-top: 50px;
}
.btn {
  font-size: 18px;
  margin: auto;
}
.msg-container {
  margin-left: 200px;
}
.time {
  margin-left: 24px;
  font-size: 12px;
}
</style>
<script type="text/javascript">
var app = new Vue({
  el: '#app',
  data: {
    sendResult: {},
    messages: [],
  },
  methods: {
    sendWebMsg: function() {
      axios.get('/push/webmsg').then(res => {
        this.sendResult = res.data;
      });
    }
  }
});

var url = "http://localhost:2009/sockjs/webmsg";
var sock = new SockJS(url);
sock.onopen = function() {
  console.log('Socket Opened!');
};
sock.onmessage = function(e) {
  var message = JSON.parse(e.data);
  message.timeStamp = e.timeStamp;
  app.messages.push(message);
};
sock.onclose = function() {
  console.log('Socket Closed!');
};
sock.onerror = function() {
  console.error('Socket Error!');
};
</script>
</body>
</html>