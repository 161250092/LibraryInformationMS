Vue.http.options.emulateJSON = true;

var vm =new Vue({

    el:"#app",
    data() {
        return {

            users:[{
                userId:'',
                userName:'',
                password:'',
                balance:'',
                borrowingNum:''
            }],


            //编辑表
            editor: {
                userName:'',
                password:'',
                balance:'',
            },

            //编辑dialog
            showEditor: false,

            selectId:'',

            count: 0, //tag栏，此项那是checkbox选择了几行
            //分页选项
            pageConf: {
                //设置一些初始值(会被覆盖)
                pageCode: 1, //当前页
                pageSize: 6, //每页显示的记录数
                totalPage: 12, //总记录数
                pageOption: [6, 10, 20], //分页选项
            },

            loading: {},

            dialogVisible: false,

            activeIndex: '2', //默认激活
        }

    },


    methods: {
        loadings() {
            this.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.loading.close();
            }, 2000);
        },

        //分页获取书籍信息
        search(pageCode, pageSize) {
            this.loadings();
            this.$http.post('/books/findUsersByConPage?pageSize=' + pageSize + '&pageCode=' + pageCode).then(result => {
                console.log(result);
                this.users = result.body.rows;
                this.pageConf.totalPage = result.body.total;
                this.loading.close(); //数据更新成功就手动关闭动画
            });

        },

        reloadList() {
            console.log("totalPage:" + this.pageConf.totalPage + ",pageSize:" + this.pageConf.pageSize + ",:pageCode:" + this.pageConf.pageCode);
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },


        //pageSize改变时触发的函数
        handleSizeChange(val) {
            this.search(this.pageConf.pageCode, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            this.pageConf.pageCode = val; //为了保证刷新列表后页面还是在当前页，而不是跳转到第一页
            this.search(val, this.pageConf.pageSize);
        },


        //单个编辑
        handleEdit(id,name) {
            //打开dialog
            console.log(id);
            this.selectId = id;
            this.editor.userName = name;
            this.showEditor = true;
            this.editor = {};
        },

        //修改
        sureEdit() {
            //关闭对话框
            this.showEditor = false;
            //调用更新数据的接口
            this.$http.post('/users/updateUserInformation',
                {
                    userId:this.selectId,
                    userName:this.editor.userName,
                    password:this.editor.password,
                    balance:this.editor.balance
                }).then(result => {
                if (result.body.success) {
                    //更新成功
                    this.$message({
                        type: 'success',
                        message: result.body.message,
                        duration: 6000
                    });
                    //刷新列表
                    this.reloadList();
                    this.books = [];
                    this.$refs.editor.resetFields();
                } else {
                    //更新失败
                    this.$message({
                        type: 'warning',
                        message: result.body.message,
                        duration: 6000
                    });
                    //刷新列表
                    this.reloadList();
                    this.$refs.editor.resetFields();
                }
            })
        },

    },
    created(){
        // this.findAll();
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.loadings(); //加载动画
    }

})