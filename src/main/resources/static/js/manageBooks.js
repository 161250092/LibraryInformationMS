//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

var vm =new Vue({

    el:"#app",
    data() {
        return {

            books: [{
                bookId: '',
                title: '',
                totalNum: '',
                borrowedNum: ''
            }],

            //编辑表
            editor: {
                title: '',
                totalNum: '',
                borrowedNum: ''
            },

            //添加dialog
            showSave: false,
            //编辑dialog
            showEditor: false,

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

            //删
            sureDelete(ids) {
                this.$confirm('你确定永久删除此书籍信息？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    center: true
                }).then(() => {
                    //调用删除的接口(这里必须将数据转换成JSON格式，不然接收不到值，并且后端要用@RequestBody注解标识)
                    this.$http.post('/books/delete', JSON.stringify(ids)).then(result => {
                        if (result.body.success) {
                            //删除成功
                            this.selectIds = []; //清空选项
                            this.$message({
                                type: 'success',
                                message: result.body.message,
                                duration: 6000
                            });
                            //刷新列表
                            //为什么要判断并赋值？
                            //答：即使调用reloadList()刷新列表，但是对于删除，在reloadList()中获取到的totalPage总记录和pageCode当前页都是未删除之前的记录，当遇到删除此页的最后一个记录时，页码会自动跳到上一页，但是table中的数据显示"暂无记录"
                            //   所以要判断，如果是删除此页的最后一条记录，删除后自动跳转到前一页，数据也是前一页的数据
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


            //新建
            save(editor) {
                this.$refs[editor].validate((valid) => {
                    if (valid) {
                        //关闭dialog
                        this.showSave = false;
                        //调用保存的接口
                        this.$http.post('/books/create', JSON.stringify(this.editor)).then(result => {
                            if (result.body.success) {
                                //保存成功
                                this.$message({
                                    type: 'success',
                                    message: result.body.message,
                                    duration: 6000
                                });
                                //刷新表格
                                this.reloadList();
                                this.editor = {};
                                this.$refs.editor.resetFields();
                            } else {
                                //保存失败
                                this.$emit(
                                    'save',
                                    this.$message({
                                        type: 'warning',
                                        message: result.body.message,
                                        duration: 6000
                                    }),
                                );
                                //刷新表格
                                this.reloadList();
                                this.editor = {};
                                this.$refs.editor.resetFields();
                            }
                        });
                    } else {
                        this.$emit(
                            'save',
                            this.$message({
                                message: '输入信息有误！',
                                type: 'warning',
                                duration: 6000
                            }),
                        );
                        return false;
                    }
                });
            },

            //新增按钮
            saveBtn() {
                //打开新增dialog
                this.showSave = true;
                this.editor = {}; //清空表单

                //清空原始数据
                if (this.$refs['editor'] !== undefined) {
                    this.$refs['editor'].resetFields(); //经查询：可能是由于对象还没有生成，导致误读了空对象而报错
                }
            },


            //单个编辑
            handleEdit(id) {
                //打开dialog
                this.showEditor = true;
                this.editor = {}; //
                for( i=0;i<this.books.length;i++){
                    if(books[i].bookId.equals(id)){
                        this.editor.title = this.books[i].title;
                        this.editor.total = this.books[i].total;
                        this.editor.borrowedNum = this.books[i].borrowedNum;
                    }
                }

            },

            //修改
            sureEdit(editor) {
                //关闭对话框
                this.showEditor = false;
                //调用更新数据的接口
                this.$http.post('/books/update', JSON.stringify(this.editor)).then(result => {
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


            //单个删除
            handleDelete(id) {
                var ids = new Array();
                ids.push(id);
                this.sureDelete(ids);
            },

            //批量删除
            deleteSelect(rows) {
                if (rows) {
                    rows.forEach(row => {
                        this.selectIds.push(row.id);
                        this.$refs.books.toggleRowSelection(row);
                    });
                    //调用删除方法
                    this.sureDelete(this.selectIds);
                } else {
                    this.$refs.goods.clearSelection();
                }
            },



        },

    created(){
        // this.findAll();
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.loadings(); //加载动画
    }




})