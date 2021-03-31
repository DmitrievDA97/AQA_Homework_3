package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardApplicationTest {
    @Test
    void positiveCaseTest() {
        open("http://localhost:9999");

       $("[data-test-id=name] input").setValue("Иванов Василий");
       $("[data-test-id=phone] input").setValue("+79500004455");
       $("[data-test-id=agreement]").click();
       $("[type=button]").click();
       $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void emptyFields() {
        open("http://localhost:9999");

        $("[type=button]").click();
        $(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void emptyNameField() {
        open("http://localhost:9999");

        $("[data-test-id=phone] input").setValue("+79500004455");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void emptyPhoneField() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }
    @Test
    void emptyCheckbox() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79500004455");
        $("[type=button]").click();
        $(".input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));

    }

    @Test

    void invalidName(){
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Ivanov Vasiliy");
        $("[data-test-id=phone] input").setValue("+79500004455");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void invalidPhone() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("89500004455");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }





}
