var Main = {
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm2.checkPass !== '') {
                    this.$refs.ruleForm2.validateField('checkPass');
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm2.pass) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            ruleForm2: {
                oldPass:'',
                pass: '',
                checkPass: '',
            },
            rules2: {
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validatePass2, trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {

                    this.$http.post('/personInformation/update', {
                        oldPass:this.ruleForm2.oldPass,
                        pass: this.ruleForm2.pass,
                    }).then(result => {
                        // 判断用户是否登录成功，后端返回JSON格式数据，不然娶不到数据
                        if (result.body.success) {
                            alert("修改密码成功")
                        }
                        else {
                            // 弹出错误信息框
                            this.$emit(
                                'submit-form',
                                this.$message({
                                    message: '密码错误！',
                                    type: 'warning',
                                    duration: 6000
                                }),
                            );
                            // 清空表单状态
                            this.$refs['ruleForm2'].resetFields();
                        }
                    });


                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')