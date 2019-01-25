package cn.tycoding.controller.adminController;

import cn.tycoding.entity.Book;
import cn.tycoding.service.literature.LiteratureService;
import cn.tycoding.util.PageBean;
import cn.tycoding.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookManageController {

    @Autowired
    private LiteratureService literatureService;

    /**
     * 分页查询
     * @param pageCode 当前页
     * @param pageSize 每页显示的记录数
     * @return
     */
    @RequestMapping("/findByConPage")
    public PageBean findByConPage(
                                  @RequestParam(value = "pageCode", required = false) int pageCode,
                                  @RequestParam(value = "pageSize", required = false) int pageSize) {
        return literatureService.findByConPage(pageCode, pageSize);
    }

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long... ids) {

        try {
            for(long bookId:ids)
            literatureService.deleteBook(bookId);

            return new Result(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 创建新书
     * @param book
     * @return
     */
    @RequestMapping("/create")
    public Result create(@RequestBody Book book){
        try{
            literatureService.addBook(book);

            return new Result(true, "创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 更新书籍信息
     * @param
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestParam("bookId")long bookId,@RequestParam("title")String title,@RequestParam("totalNum")int totalNum,
    @RequestParam("borrowedNum")int borrowedNum){
        try{
         //   literatureService.modifyBook(book);
            return new Result(true, "创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }





}
