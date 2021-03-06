package com.pang.book.controller;

public class IndexController {
//
//    @Autowired
//    private MessageService messageService;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private CommonService commonService;
//
//    /**
//     * 主页
//     *
//     * @param map
//     * @param session
//     * @return java.lang.String
//     * @author pang
//     * @date 2018/12/24
//     */
//    @RequestMapping("/")
//    public String index(ModelMap map, HttpSession session) {
//        // 加入一个属性，用来在模板中读取
//        User user = (User) session.getAttribute("user");
//        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM); //显示日期，时间（精确到分）
//        List<Message> messageList = messageService.findAllMessages();
//        if (user != null) {
//            map.addAttribute("user", user);
//        } else {
//            map.addAttribute("user", null);
//        }
//        if (messageList != null) {
//            map.addAttribute("messagelist", messageList);
//            System.out.println(messageList.get(1).getMessageTime().toString());
//        }
//        map.addAttribute("author", "全部");
//        map.addAttribute("date", df);
//        // return模板文件的名称，对应src/main/resources/templates/index.html
//        return "index";
//    }
//
//
//    /**
//     * 添加评论
//     *
//     * @param message
//     * @param session
//     * @return java.lang.String
//     * @author pang
//     * @date 2018/12/24
//     */
//    @GetMapping("/message/add")
//    public String addMessage(Message message, HttpSession session) {
//        User writer = (User) session.getAttribute("user");
//        message.setUser(writer);
//        messageService.update(message);
//        return "redirect:/";
//    }
//
//    /**
//     * 添加回复
//     *
//     * @param common
//     * @param session
//     * @param messageId
//     * @return java.lang.String
//     * @author pang
//     * @date 2018/12/25
//     */
//    @GetMapping("/common/add/{id}")
//    public String addCommon(Common common, HttpSession session, @PathVariable(name = "id") int messageId) {
//        User from = (User) session.getAttribute("user");
//        Message message = messageService.findById(messageId);
//        common.setMessageId(message.getMessageId());
//        common.setCommonFrom(from.getUserId());
//        commonService.insert(common);
//        return "redirect:/";
//    }
//
//    /**
//     * 删除回复
//     *
//     * @param commonId
//     * @return java.lang.String
//     * @author pang
//     * @date 2018/12/25
//     */
//    @GetMapping("/common/delete/{id}")
//    public String deleteCommon(@PathVariable(name = "id") int commonId) {
//        commonService.deleteById(commonId);
//        return "redirect:/";
//    }
//
//    /**
//     * 删除评论
//     *
//     * @param msgId
//     * @return java.lang.String
//     * @author pang
//     * @date 2018/12/25
//     */
//    @GetMapping("/message/delete/{msgId}")
//    public String deleteMessage(@PathVariable(name = "msgId") int msgId) {
//        messageService.deleteById(msgId);
//        return "redirect:/";
//    }
//
//    /**
//     * 编辑评论
//     *
//     * @param msgId
//     * @param text
//     * @return
//     */
//    @GetMapping("/message/edit/{msgId}")
//    public String editMessage(@PathVariable(name = "msgId") int msgId, String text) {
//        Message message = messageService.findById(msgId);
//        message.setMessageContent(text);
//        messageService.update(message);
//        return "redirect:/";
//    }
//
//    /**
//     * 编辑回复
//     *
//     * @param commonId
//     * @param text
//     * @return
//     */
//    @GetMapping("/common/edit/{commonId}")
//    public String editCommon(@PathVariable(name = "commonId") int commonId, String text) {
//        Common common = commonService.findById(commonId);
//        common.setCommonContent(text);
//        commonService.update(common);
//        return "redirect:/";
//    }
}
