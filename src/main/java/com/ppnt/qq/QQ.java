package com.ppnt.qq;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litongjava.utils.reflection.ClassPathUtils;
import com.ppnt.qq.utils.ImageIconUtils;

@SuppressWarnings("serial")
public class QQ extends JFrame {

  private static final String username_hint = " QQ号码/手机/邮箱";
  private static final Logger log = LoggerFactory.getLogger(QQ.class);
  /**
   * 1.jbutton添加鼠标监听实现图片变化效果不如jlabel好，故下面许多组件都用jlabel代替（好像有时候会点击没反应）
   * 2.图片素材来源于果核剥壳，代码中未用到的可自行收藏利用
   * 3.各按钮动画判断条件自行测试体会
   * 4.本人也水平一般，不足之处可自行更改优化，并欢迎在我的帖子下进行交流讨论
   * 
   * @author MingKing
   */

  private JLabel block;

  int xOld = 0;
  int yOld = 0;
  private JLabel checkboxp;
  private JLabel checkboxl;

  public QQ() {
    // 顶层容器
    setUndecorated(true);// 去除默认标题栏
    setSize(430, 330);// 设置窗口大小
    setFocusable(true);// 获取焦点，防止启动时焦点在输入qq号的窗口上
    this.setLocationRelativeTo(null);// 窗口居中

    // 中间容器
    JPanel contentPane = new JPanel();
    contentPane.setBackground(new Color(234, 241, 248));
    contentPane.setLayout(null);
    setContentPane(contentPane);

    // 关闭按钮
    final JLabel close = new JLabel(ImageIconUtils.getImageIcon("/LoginFrame/Btn_Login/btn_close_normal.png"));
    close.setBounds(400, 0, 30, 27);
    close.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        // 处理鼠标点击
        System.exit(0);
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        close.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/btn_close_highlight.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        if (e.getModifiers() == 0)
          close.setIcon(ImageIconUtils.getImageIcon("/LoginFrame/Btn_Login/btn_close_normal.png"));
      }

      public void mousePressed(MouseEvent e) {
        // 处理鼠标按下
        close.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/btn_close_down.png")));
      }

      public void mouseReleased(MouseEvent e) {
        // 处理鼠标释放
        close.setIcon(ImageIconUtils.getImageIcon("/LoginFrame/Btn_Login/btn_close_normal.png"));
      }
    });
    contentPane.add(close);

    // 登陆状态图标（未设置点击事件）
    JLabel state = new JLabel(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/status/imonline.png")));
    state.setOpaque(false);
    state.setBorder(null);
    state.setBounds(100, 261, 15, 15);
    contentPane.add(state);

    // 最小化按钮
    final JLabel minimize = new JLabel(
        new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/btn_mini_normal.png")));
    minimize.setBounds(370, 0, 30, 27);
    minimize.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        // 处理鼠标点击
        QQ.this.setExtendedState(JFrame.ICONIFIED);
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        minimize.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/btn_mini_highlight.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        if (e.getModifiers() == 0)
          minimize.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/btn_mini_normal.png")));
      }

      public void mousePressed(MouseEvent e) {
        // 处理鼠标按下
        minimize.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/btn_mini_down.png")));
      }

      public void mouseReleased(MouseEvent e) {
        // 处理鼠标释放
        minimize.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/btn_mini_normal.png")));
      }
    });
    contentPane.add(minimize);

    // 箭头按钮
    final JLabel arrow = new JLabel(
        new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/aio_setting_white_normal.png")));
    arrow.setBounds(340, 0, 30, 27);
    arrow.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        // 处理鼠标点击
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        arrow.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/aio_setting_white_hover.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        if (e.getModifiers() == 0)
          arrow
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/aio_setting_white_normal.png")));
      }

      public void mousePressed(MouseEvent e) {
        // 处理鼠标按下
        arrow.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/aio_setting_white_down.png")));
      }

      public void mouseReleased(MouseEvent e) {
        // 处理鼠标释放
        if (e.getX() <= arrow.getWidth() && e.getX() >= 0 && e.getY() <= arrow.getHeight() && e.getY() >= 0)
          arrow.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/aio_setting_white_hover.png")));
        else
          arrow
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/aio_setting_white_normal.png")));
      }
    });
    contentPane.add(arrow);

    // qq标志
    JLabel logo = new JLabel(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/logo-banner.png")));
    logo.setBounds(155, 65, 129, 64);
    contentPane.add(logo);

    // 调整头像图片大小
    ImageIcon headImage = new ImageIcon(ClassPathUtils.getResource("/timg.jpg"));
    headImage.setImage(headImage.getImage().getScaledInstance(82, 82, Image.SCALE_DEFAULT));

    // 图片加入到头像标签中
    JLabel head = new JLabel(headImage);
    head.setBounds(34, 195, 82, 82);
    contentPane.add(head);

    // 左下角人头按钮
    final JLabel people = new JLabel(
        new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/switch_single_normal.png")));
    people.setBounds(6, 300, 24, 24);
    people.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        // 处理鼠标点击
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        people.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/switch_single_hover.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        if (e.getModifiers() == 0)
          people.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/switch_single_normal.png")));
      }

      public void mousePressed(MouseEvent e) {
        // 处理鼠标按下
        people.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/switch_single_down.png")));
      }

      public void mouseReleased(MouseEvent e) {
        // 处理鼠标释放
        if (e.getX() <= people.getWidth() && e.getX() >= 0 && e.getY() <= people.getHeight() && e.getY() >= 0)
          people.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/switch_single_hover.png")));
        else
          people.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/switch_single_normal.png")));
      }
    });
    contentPane.add(people);

    // 右下角二维码按钮
    final JLabel code = new JLabel(
        new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/corner_right_normal_breath.png")));
    code.setBounds(400, 300, 24, 24);
    code.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        // 处理鼠标点击
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        code.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/corner_right_hover.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        if (e.getModifiers() == 0)
          code.setIcon(
              new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/corner_right_normal_breath.png")));
      }

      public void mousePressed(MouseEvent e) {
        // 处理鼠标按下
        code.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/corner_right_normal_down.png")));
      }

      public void mouseReleased(MouseEvent e) {
        // 处理鼠标释放
        if (e.getX() <= code.getWidth() && e.getX() >= 0 && e.getY() <= code.getHeight() && e.getY() >= 0)
          code.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/corner_right_hover.png")));
        else
          code.setIcon(
              new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/corner_right_normal_breath.png")));
      }
    });
    contentPane.add(code);

    // 密码框中键盘按钮
    final JLabel keyboard = new JLabel(
        new ImageIcon(ClassPathUtils.getResource("/LoginFrame/PasswordEdit_Keybd/keyboard_normal.png")));
    keyboard.setBounds(298, 231, 15, 16);
    keyboard.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        // 处理鼠标点击
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        keyboard
            .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/PasswordEdit_Keybd/keyboard_hover.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        if (e.getModifiers() == 0)
          keyboard
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/PasswordEdit_Keybd/keyboard_normal.png")));
      }

      public void mousePressed(MouseEvent e) {
        // 处理鼠标按下
        keyboard.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/PasswordEdit_Keybd/keyboard_down.png")));
      }

      public void mouseReleased(MouseEvent e) {
        // 处理鼠标释放
        if (e.getX() <= keyboard.getWidth() && e.getX() >= 0 && e.getY() <= keyboard.getHeight() && e.getY() >= 0)
          keyboard
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/PasswordEdit_Keybd/keyboard_hover.png")));
        else
          keyboard
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/PasswordEdit_Keybd/keyboard_normal.png")));
      }
    });

    // 记住密码面板
    final JPanel remenber = new JPanel();
    remenber.setOpaque(false);
    remenber.setBounds(131, 260, 72, 18);
    remenber.addMouseListener(new MouseListener() {
      boolean state = false;

      public void mouseClicked(MouseEvent e) {
        // 处理鼠标点击
        state = !state;
        if (state)
          checkboxp
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/Checkbox_tick_highlight1.png")));
        else
          checkboxp.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_highlight.png")));
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        if (state)
          checkboxp
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/Checkbox_tick_highlight1.png")));
        else
          checkboxp.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_highlight.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        if (e.getModifiers() == 0)
          if (state)
            checkboxp
                .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/Checkbox_tick_normal1.png")));
          else
            checkboxp.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_normal.png")));
      }

      public void mousePressed(MouseEvent e) {
        // 处理鼠标按下
        if (state)
          checkboxp
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/Checkbox_tick_pushed1.png")));
        else
          checkboxp.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_down.png")));
      }

      public void mouseReleased(MouseEvent e) {
        // 处理鼠标释放
        if (e.getX() <= remenber.getWidth() && e.getX() >= 0 && e.getY() <= remenber.getHeight() && e.getY() >= 0)
          if (state)
            checkboxp.setIcon(
                new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/Checkbox_tick_highlight1.png")));
          else
            checkboxp.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_highlight.png")));
        else if (state)
          checkboxp
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/Checkbox_tick_normal1.png")));
        else
          checkboxp.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_normal.png")));
      }
    });
    contentPane.add(remenber);
    remenber.setLayout(null);

    // 复选框
    checkboxp = new JLabel(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_normal.png")));
    checkboxp.setBounds(0, 0, 17, 17);
    remenber.add(checkboxp);

    // 复选框对应文字
    JLabel rpw = new JLabel("记住密码");
    rpw.setFont(new Font("微软雅黑", Font.PLAIN, 12));
    rpw.setForeground(Color.gray);
    rpw.setBounds(17, 0, 55, 17);
    remenber.add(rpw);

    // 自动登陆面板
    final JPanel auto = new JPanel();
    auto.setLayout(null);
    auto.setOpaque(false);
    auto.setBounds(253, 260, 72, 18);
    auto.addMouseListener(new MouseListener() {
      boolean state = false;

      public void mouseClicked(MouseEvent e) {
        // 处理鼠标点击
        state = !state;
        if (state)
          checkboxl
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/Checkbox_tick_highlight1.png")));
        else
          checkboxl.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_highlight.png")));
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        if (state)
          checkboxl
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/Checkbox_tick_highlight1.png")));
        else
          checkboxl.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_highlight.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        if (e.getModifiers() == 0)
          if (state)
            checkboxl
                .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/Checkbox_tick_normal1.png")));
          else
            checkboxl.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_normal.png")));
      }

      public void mousePressed(MouseEvent e) {
        // 处理鼠标按下
        if (state)
          checkboxl
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/Checkbox_tick_pushed1.png")));
        else
          checkboxl.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_down.png")));
      }

      public void mouseReleased(MouseEvent e) {
        // 处理鼠标释放
        if (e.getX() <= auto.getWidth() && e.getX() >= 0 && e.getY() <= auto.getHeight() && e.getY() >= 0)
          if (state)
            checkboxl.setIcon(
                new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/Checkbox_tick_highlight1.png")));
          else
            checkboxl.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_highlight.png")));
        else if (state)
          checkboxl
              .setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/Checkbox_tick_normal1.png")));
        else
          checkboxl.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_normal.png")));
      }
    });
    contentPane.add(auto);

    // 复选框
    checkboxl = new JLabel(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/CheckBox/CheckBox_normal.png")));
    checkboxl.setBounds(0, 0, 17, 17);
    auto.add(checkboxl);

    // 复选框对应文字
    JLabel al = new JLabel("自动登录");
    al.setFont(new Font("微软雅黑", Font.PLAIN, 12));
    al.setForeground(Color.GRAY);
    al.setBounds(17, 0, 55, 17);
    auto.add(al);
    contentPane.add(keyboard);

    // QQ号输入框
    final JTextField username = new JTextField(username_hint);
    username.setBorder(null);
    username.setForeground(Color.gray);
    username.setBounds(131, 198, 190, 27);
    username.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        block.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Editbk_top_hover.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        block.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Editbk_normal.png")));
      }

      public void mousePressed(MouseEvent e) {
      }

      public void mouseReleased(MouseEvent e) {
      }
    });
    username.addFocusListener(new FocusAdapter() {
      public void focusGained(FocusEvent e) {
        if (username.getText().equals(username_hint)) {
          username.setText("");
          username.setForeground(Color.black);
        }
      }

      public void focusLost(FocusEvent e) {
        if (username.getText().equals("")) {
          username.setText(username_hint);
          username.setForeground(Color.gray);
        }
      }
    });
    contentPane.add(username);

    // 密码框
    final JPasswordField password = new JPasswordField(" 密码");
    password.setOpaque(false);
    password.setBorder(null);
    password.setForeground(Color.gray);
    password.setEchoChar((char) 0);
    password.setBounds(131, 227, 190, 27);
    password.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        block.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Editbk_bottom_hover.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        block.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Editbk_normal.png")));
      }

      public void mousePressed(MouseEvent e) {
      }

      public void mouseReleased(MouseEvent e) {
      }
    });
    password.addFocusListener(new FocusAdapter() {
      public void focusGained(FocusEvent e) {
        if (password.getText().equals(" 密码")) {
          password.setText("");
          password.setForeground(Color.black);
          password.setEchoChar("*".charAt(0));
        }
      }

      public void focusLost(FocusEvent e) {
        if (password.getText().equals("")) {
          password.setText(" 密码");
          password.setForeground(Color.gray);
          password.setEchoChar((char) 0);
        }
      }
    });
    contentPane.add(password);

    // 用户名、密码动画框
    block = new JLabel(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Editbk_normal.png")));
    block.setBounds(127, 195, 198, 61);
    contentPane.add(block);

    // 注册按钮
    final JLabel register = new JLabel(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/zhuce.png")));
    register.setBounds(335, 205, 48, 11);
    register.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        // 处理鼠标点击
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        register.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/zhuce_hover.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        if (e.getModifiers() == 0)
          register.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/zhuce.png")));
      }

      public void mousePressed(MouseEvent e) {
        // 处理鼠标按下
        register.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/zhuce_press.png")));
      }

      public void mouseReleased(MouseEvent e) {
        // 处理鼠标释放
        if (e.getX() <= register.getWidth() && e.getX() >= 0 && e.getY() <= register.getHeight() && e.getY() >= 0)
          register.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/zhuce_hover.png")));
        else
          register.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/zhuce.png")));
      }
    });
    contentPane.add(register);

    // 找回密码按钮
    final JLabel findpassword = new JLabel(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/mima.png")));
    findpassword.setBounds(335, 234, 48, 11);
    findpassword.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        // 处理鼠标点击
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        findpassword.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/mima_hover.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        if (e.getModifiers() == 0)
          findpassword.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/mima.png")));
      }

      public void mousePressed(MouseEvent e) {
        // 处理鼠标按下
        findpassword.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/mima_press.png")));
      }

      public void mouseReleased(MouseEvent e) {
        // 处理鼠标释放
        if (e.getX() <= findpassword.getWidth() && e.getX() >= 0 && e.getY() <= findpassword.getHeight()
            && e.getY() >= 0)
          findpassword.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/mima_hover.png")));
        else
          findpassword.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/mima.png")));
      }
    });
    contentPane.add(findpassword);

    // 登陆按钮
    final JLabel login = new JLabel(
        new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/button_login_normal.png")));
    login.setBounds(131, 285, 194, 30);
    login.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        // 处理鼠标点击
      }

      public void mouseEntered(MouseEvent e) {
        // 处理鼠标移入
        login.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/button_login_hover.png")));
      }

      public void mouseExited(MouseEvent e) {
        // 处理鼠标离开
        if (e.getModifiers() == 0)
          login.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/button_login_normal.png")));
      }

      public void mousePressed(MouseEvent e) {
        // 处理鼠标按下
        login.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/button_login_down.png")));
      }

      public void mouseReleased(MouseEvent e) {
        // 处理鼠标释放
        if (e.getX() <= login.getWidth() && e.getX() >= 0 && e.getY() <= login.getHeight() && e.getY() >= 0)
          login.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/button_login_hover.png")));
        else
          login.setIcon(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/Btn_Login/button_login_normal.png")));
      }
    });

    // 登录标签
    JLabel logintext = new JLabel("登    录");
    logintext.setFont(new Font("微软雅黑", Font.PLAIN, 12));
    logintext.setForeground(new Color(255, 255, 255));
    logintext.setHorizontalAlignment(SwingConstants.CENTER);
    logintext.setBounds(131, 285, 194, 30);
    contentPane.add(logintext);
    contentPane.add(login);

    // 蓝色背景图片
    JLabel background = new JLabel(new ImageIcon(ClassPathUtils.getResource("/LoginFrame/bk.jpg")));
    background.setSize(430, 184);
    contentPane.add(background);

    // 蓝色背景图片添加鼠标拖动功能
    background.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        xOld = e.getX();// 记录鼠标按下时的坐标
        yOld = e.getY();
      }
    });
    background.addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        int xOnScreen = e.getXOnScreen();
        int yOnScreen = e.getYOnScreen();
        int x = xOnScreen - xOld;
        int y = yOnScreen - yOld;
        QQ.this.setLocation(x, y);// 设置拖拽后，窗口的位置
      }
    });

  }

  // main方法
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          QQ frame = new QQ();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
