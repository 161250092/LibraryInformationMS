Vue.http.options.emulateJSON = true;

var vm =new Vue({

    el: "#app",
    data() {
        return {

            books: [{
                bookId: '',
                title: '',
                totalNum: '',
                borrowedNum: ''
            }],


            //checkbox选择的行中所有数据，将会放入multipartSelection数组中
            multipleSelection: [],

            //被checkbox选择的id值，用于批量删除
            selectIds: [],


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
            this.$http.post('/books/findByConPage?pageSize=' + pageSize + '&pageCode=' + pageCode).then(result => {
                console.log(result);

                this.books = result.body.rows;
                this.pageConf.totalPage = result.body.total;
                this.loading.close(); //数据更新成功就手动关闭动画
            });

        },

        reloadList() {
            console.log("totalPage:" + this.pageConf.totalPage + ",pageSize:" + this.pageConf.pageSize + ",:pageCode:" + this.pageConf.pageCode);
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },


        selectChange(val) {
            this.count = val.length;
            this.multipleSelection = val;
        },

        clearSelect() {
            this.$refs.books.clearSelection();
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

        //借书
        borrowBook(ids) {
            this.$confirm('你确定借阅该书籍吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post('/borrowBooks', JSON.stringify(ids)).then(result => {
                    if (result.body.success) {

                        this.selectIds = []; //清空选项
                        this.$message({
                            type: 'success',
                            message: result.body.message,
                            duration: 6000
                        });

                        if ((this.pageConf.totalPage - 1) / this.pageConf.pageSize === (this.pageConf.pageCode - 1)) {
                            this.pageConf.pageCode = this.pageConf.pageCode - 1;
                        }
                        this.reloadList();
                    } else {
                        //删除失败
                        this.selectIds = []; //清空选项
                        this.$message({
                            type: 'warning',
                            message: result.body.message,
                            duration: 6000
                        });
                        //刷新列表
                        this.reloadList();
                    }
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除',
                    duration: 6000
                });
            });
        },
        //还书
        returnBook(ids) {
            this.$confirm('你确定归还该书籍吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post('/returnBooks', JSON.stringify(ids)).then(result => {
                    if (result.body.success) {

                        this.selectIds = []; //清空选项
                        this.$message({
                            type: 'success',
                            message: result.body.message,
                            duration: 6000
                        });

                        if ((this.pageConf.totalPage - 1) / this.pageConf.pageSize === (this.pageConf.pageCode - 1)) {
                            this.pageConf.pageCode = this.pageConf.pageCode - 1;
                        }
                        this.reloadList();
                    } else {
                        //删除失败
                        this.selectIds = []; //清空选项
                        this.$message({
                            type: 'warning',
                            message: result.body.message,
                            duration: 6000
                        });
                        //刷新列表
                        this.reloadList();
                    }
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除',
                    duration: 6000
                });
            });
        },

        readBooks(id){


        }

    },

    created() {
        // this.findAll();
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.loadings(); //加载动画
    }
});
