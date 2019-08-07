package com.dzz.medical.service.impl;

import com.dzz.medical.common.enums.OrganizationEnum;
import com.dzz.medical.common.enums.QuestionType;
import com.dzz.medical.supervise.domain.model.Answer;
import com.dzz.medical.supervise.domain.model.Question;
import com.dzz.medical.supervise.service.impl.QuestionServiceMongoImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月15 17:11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionServiceMongoImplTest {

    private QuestionServiceMongoImpl questionService;


    @Autowired
    public void setQuestionService(QuestionServiceMongoImpl questionService) {
        this.questionService = questionService;
    }

    @Test
    public void saveQuestion() {

        List<Question> questions = publicQuestion1();
        for(Question question : questions) {
            questionService.saveQuestion(question);
        }
    }

    /**
     * 公共场所放射卫生
     * @return 结果
     */
    public List<Question> publicQuestion1() {
        List<Question> questions = new ArrayList<>();

        Question question = new Question();
        question.setBelongIndustry(2);
        question.setBelongOrganization(OrganizationEnum.MEDICAL.getCode());
        question.setQuestionName("《放射诊疗卫生许可证》是否在有效期内，并按期校验？");
        question.setQuestionNo(1);
        question.setQuestionType(QuestionType.SELECT.getCode());
        question.setQuestionTypeName(QuestionType.SELECT.getName());
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("是", "A、"));
        answers.add(new Answer("否", "B、"));
        question.setAnswers(answers);
        questions.add(question);


        question = new Question();
        question.setBelongIndustry(2);
        question.setBelongOrganization(OrganizationEnum.MEDICAL.getCode());
        question.setQuestionName("本周期内是否开展辐射工作场所检测？");
        question.setQuestionNo(2);
        question.setQuestionType(QuestionType.SELECT.getCode());
        question.setQuestionTypeName(QuestionType.SELECT.getName());
        answers = new ArrayList<>();
        answers.add(new Answer("是", "A、"));
        answers.add(new Answer("否", "B、"));
        question.setAnswers(answers);
        questions.add(question);


        question = new Question();
        question.setBelongIndustry(2);
        question.setBelongOrganization(OrganizationEnum.MEDICAL.getCode());
        question.setQuestionName("本周期内是否开放射诊疗设备性能检测？");
        question.setQuestionNo(3);
        question.setQuestionType(QuestionType.SELECT.getCode());
        question.setQuestionTypeName(QuestionType.SELECT.getName());
        answers = new ArrayList<>();
        answers.add(new Answer("是", "A、"));
        answers.add(new Answer("否", "B、"));
        question.setAnswers(answers);
        questions.add(question);


        question = new Question();
        question.setBelongIndustry(2);
        question.setBelongOrganization(OrganizationEnum.MEDICAL.getCode());
        question.setQuestionName("放射工作人员  $  人，取得有效放射防护和有关法律知识培训合格证  $  人。");
        question.setQuestionNo(4);
        question.setQuestionType(QuestionType.FILL_IN_BLANK.getCode());
        question.setQuestionTypeName(QuestionType.FILL_IN_BLANK.getName());
        answers = new ArrayList<>();
        question.setAnswers(answers);
        questions.add(question);

        question = new Question();
        question.setBelongIndustry(2);
        question.setBelongOrganization(OrganizationEnum.MEDICAL.getCode());
        question.setQuestionName("是否佩戴个人剂量计？");
        question.setQuestionNo(1);
        question.setQuestionType(QuestionType.SELECT.getCode());
        question.setQuestionTypeName(QuestionType.SELECT.getName());
        answers = new ArrayList<>();
        answers.add(new Answer("是", "A、"));
        answers.add(new Answer("否", "B、"));
        question.setAnswers(answers);
        questions.add(question);

        question = new Question();
        question.setBelongIndustry(2);
        question.setBelongOrganization(OrganizationEnum.MEDICAL.getCode());
        question.setQuestionName("本季度开展个人剂量监测  $  人。");
        question.setQuestionNo(1);
        question.setQuestionType(QuestionType.FILL_IN_BLANK.getCode());
        question.setQuestionTypeName(QuestionType.FILL_IN_BLANK.getName());
        answers = new ArrayList<>();
        question.setAnswers(answers);
        questions.add(question);

        question = new Question();
        question.setBelongIndustry(2);
        question.setBelongOrganization(OrganizationEnum.MEDICAL.getCode());
        question.setQuestionName("是否向受检者提供防护用品？");
        question.setQuestionNo(1);
        question.setQuestionType(QuestionType.SELECT.getCode());
        question.setQuestionTypeName(QuestionType.SELECT.getName());
        answers = new ArrayList<>();
        answers.add(new Answer("是", "A、"));
        answers.add(new Answer("否", "B、"));
        question.setAnswers(answers);
        questions.add(question);

        return questions;
    }


    /**
     * 公共场所问题组装【住宿】
     * @return 结果
     */
    public List<Question> publicQuestion() {
        List<Question> questions = new ArrayList<>();


        Question question1 = new Question();
        question1.setBelongOrganization(OrganizationEnum.PUBLIC.getCode());
        question1.setBelongIndustry(1);
        question1.setQuestionName("《公共场所卫生许可证》是否在有效期内？");
        question1.setQuestionNo(1);
        question1.setQuestionType(QuestionType.SELECT.getCode());
        question1.setQuestionTypeName(QuestionType.SELECT.getName());
        List<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer("是", "A、"));
        answers1.add(new Answer("否", "B、"));
        question1.setAnswers(answers1);
        questions.add(question1);

        Question question2 = new Question();
        question2.setBelongOrganization(OrganizationEnum.PUBLIC.getCode());
        question2.setBelongIndustry(1);
        question2.setQuestionName("从业人员一共有  $   人，其中健康证有效期内   $  人，卫生知识培训合格证  $   人。");
        question2.setQuestionNo(2);
        question2.setQuestionType(QuestionType.FILL_IN_BLANK.getCode());
        question2.setQuestionTypeName(QuestionType.FILL_IN_BLANK.getName());
        List<Answer> answers2 = new ArrayList<>();
        question2.setAnswers(answers2);
        questions.add(question2);

        Question question3 = new Question();
        question3.setBelongOrganization(OrganizationEnum.PUBLIC.getCode());
        question3.setBelongIndustry(1);
        question3.setQuestionName("干净布草是否密闭存放？布草间（柜）内是否干净整洁无杂物？");
        question3.setQuestionNo(3);
        question3.setQuestionType(QuestionType.SELECT.getCode());
        question3.setQuestionTypeName(QuestionType.SELECT.getName());
        List<Answer> answers3 = new ArrayList<>();
        answers3.add(new Answer("是", "A、"));
        answers3.add(new Answer("否", "B、"));
        question3.setAnswers(answers3);
        questions.add(question3);

        Question question4 = new Question();
        question4.setBelongOrganization(OrganizationEnum.PUBLIC.getCode());
        question4.setBelongIndustry(1);
        question4.setQuestionName("客用物品是否做到一客一换一消毒？是否未重复使用一次性物品？更换记录是否齐全？");
        question4.setQuestionNo(4);
        question4.setQuestionType(QuestionType.SELECT.getCode());
        question4.setQuestionTypeName(QuestionType.SELECT.getName());
        List<Answer> answers4 = new ArrayList<>();
        answers4.add(new Answer("是", "A、"));
        answers4.add(new Answer("否", "B、"));
        question4.setAnswers(answers4);
        questions.add(question4);

        Question question5 = new Question();
        question5.setBelongOrganization(OrganizationEnum.PUBLIC.getCode());
        question5.setBelongIndustry(1);
        question5.setQuestionName("消毒用的消毒桶、消毒池、消毒柜等设施设备是否正常使用？消毒液是否未过期？消毒记录是否齐全？");
        question5.setQuestionNo(5);
        question5.setQuestionType(QuestionType.SELECT.getCode());
        question5.setQuestionTypeName(QuestionType.SELECT.getName());
        List<Answer> answers5 = new ArrayList<>();
        answers5.add(new Answer("是", "A、"));
        answers5.add(new Answer("否", "B、"));
        question5.setAnswers(answers5);
        questions.add(question5);

        Question question6 = new Question();
        question6.setBelongOrganization(OrganizationEnum.PUBLIC.getCode());
        question6.setBelongIndustry(1);
        question6.setQuestionName("送外洗消毒布草的，外洗记录是否齐全，送洗单据是否齐全？");
        question6.setQuestionNo(6);
        question6.setQuestionType(QuestionType.SELECT.getCode());
        question6.setQuestionTypeName(QuestionType.SELECT.getName());
        List<Answer> answers6 = new ArrayList<>();
        answers6.add(new Answer("是", "A、"));
        answers6.add(new Answer("否", "B、"));
        question6.setAnswers(answers6);
        questions.add(question6);

        Question question7 = new Question();
        question7.setBelongOrganization(OrganizationEnum.PUBLIC.getCode());
        question7.setBelongIndustry(1);
        question7.setQuestionName("客用非一次性塑料拖鞋是否干净无异味？鞋底是否无污渍？客用漱口杯、茶杯是否干净整洁，无茶渍水渍？");
        question7.setQuestionNo(7);
        question7.setQuestionType(QuestionType.SELECT.getCode());
        question7.setQuestionTypeName(QuestionType.SELECT.getName());
        List<Answer> answers7 = new ArrayList<>();
        answers7.add(new Answer("是", "A、"));
        answers7.add(new Answer("否", "B、"));
        question7.setAnswers(answers7);
        questions.add(question7);

        Question question8 = new Question();
        question8.setBelongOrganization(OrganizationEnum.PUBLIC.getCode());
        question8.setBelongIndustry(1);
        question8.setQuestionName("客用一次性物品是否有进货单据，是否索取生产厂家的营业执照、卫生许可证、卫生许可批件、产品检验合格报告？");
        question8.setQuestionNo(8);
        question8.setQuestionType(QuestionType.SELECT.getCode());
        question8.setQuestionTypeName(QuestionType.SELECT.getName());
        List<Answer> answers8 = new ArrayList<>();
        answers8.add(new Answer("是", "A、"));
        answers8.add(new Answer("否", "B、"));
        question8.setAnswers(answers8);
        questions.add(question8);

        Question question9 = new Question();
        question9.setBelongOrganization(OrganizationEnum.PUBLIC.getCode());
        question9.setBelongIndustry(1);
        question9.setQuestionName("大厅显眼处是否张贴“禁止吸烟”标识，是否公示“公共场所卫生许可证”、“公共场所卫生管理公示牌”“公共场所信誉等级”“卫生检测报告”。");
        question9.setQuestionNo(9);
        question9.setQuestionType(QuestionType.SELECT.getCode());
        question9.setQuestionTypeName(QuestionType.SELECT.getName());
        List<Answer> answers9 = new ArrayList<>();
        answers9.add(new Answer("是", "A、"));
        answers9.add(new Answer("否", "B、"));
        question9.setAnswers(answers9);
        questions.add(question9);

        Question question10 = new Question();
        question10.setBelongOrganization(OrganizationEnum.PUBLIC.getCode());
        question10.setBelongIndustry(1);
        question10.setQuestionName("是否建立卫生管理档案？档案内容（从业人员健康证、外洗合同、外洗记录、消毒记录、更换记录、卫生管理制度、卫生检测报告、产品卫生许可批件、卫生监督局检查记录）是否齐全？");
        question10.setQuestionNo(10);
        question10.setQuestionType(QuestionType.SELECT.getCode());
        question10.setQuestionTypeName(QuestionType.SELECT.getName());
        List<Answer> answers10 = new ArrayList<>();
        answers10.add(new Answer("是", "A、"));
        answers10.add(new Answer("否", "B、"));
        question10.setAnswers(answers10);
        questions.add(question10);

        return questions;
    }

    @Test
    public void listQuestion() {
    }
}