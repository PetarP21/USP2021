package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    private Stage window;
    private Scene scene1,scene2;
    private String[] genres = {"Екшън", "Ужаси", "Романтика", "Комедия", "Трилър", "Анимация"};
    private DataBase dataBase = new DataBase();
    private List<Movie> movies = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Филми");
        Button addButton = new Button("Добавете филм");
        addButton.setOnAction(event -> window.setScene(AddMovie()));
        Button searchButton = new Button("Търсете филм");
        searchButton.setOnAction(event -> window.setScene(SearchMovie()));

        HBox hBox = new HBox(addButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);

        HBox hBox1 = new HBox(searchButton);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setPadding(new Insets(10,10,10,10));
        hBox1.setSpacing(10);

        VBox vBox = new VBox(hBox,hBox1);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 150, 200), CornerRadii.EMPTY, Insets.EMPTY)));
        scene1 = new Scene(vBox,300,300);
        window.setScene(scene1);
        window.show();
    }

    private Scene SearchMovie() {
        if (movies != null) {
            movies.clear();
        }
        movies = DataBase.getMovies();
        Label label = new Label("Търси");
        Label namelabel = new Label("Име: ");
        Label actorslabel = new Label("Актьори: ");
        Label genrelabel = new Label("Жанр: ");
        Label yearlabel = new Label("Година: ");

        TextField name = new TextField();
        TextField actors = new TextField();
        TextField genre = new TextField();
        ComboBox<String> year = new ComboBox<String>();
        for (int i = 2021; i >= 1900; i--) {
            year.getItems().add(Integer.toString(i));
        }

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefWidth(200);
        Button button = new Button("Търси");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String res = "Филми:\n";
                boolean isNameEmpty = name.getText().equals("");
                boolean isActorsEmpty = actors.getText().equals("");
                boolean isGenreEmpty = genre.getText().equals("");
                boolean isYearEmpty = year.getSelectionModel().isEmpty();
                try {
                    for (int i = 0; i < movies.size(); i++) {
                        if (movies.get(i).getName().equals(name.getText()) // всичко
                                && movies.get(i).getActors().contains(actors.getText())
                                && movies.get(i).getGenre().equals(genre.getText())
                                && movies.get(i).getYear() == Integer.parseInt(year.getSelectionModel().getSelectedItem())) {

                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (movies.get(i).getName().equals(name.getText()) // само заглавие
                                && isActorsEmpty
                                && isGenreEmpty
                                && isYearEmpty) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (isNameEmpty // само актьори
                                && movies.get(i).getActors().contains(actors.getText())
                                && isGenreEmpty
                                && isYearEmpty) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (isNameEmpty // само жанр
                                && isActorsEmpty
                                && movies.get(i).getGenre().equals(genre.getText())
                                && isYearEmpty) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (isNameEmpty// само година
                                && isActorsEmpty
                                && isGenreEmpty
                                && movies.get(i).getYear() == Integer.parseInt(year.getSelectionModel().getSelectedItem())) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (movies.get(i).getName().equals(name.getText()) // заглавие + актьори
                                && movies.get(i).getActors().contains(actors.getText())
                                && movies.get(i).getGenre().equals(genre.getText())
                                && isYearEmpty) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (movies.get(i).getName().equals(name.getText()) // заглавие + година
                                && isActorsEmpty
                                && isGenreEmpty
                                && movies.get(i).getYear() == Integer.parseInt(year.getSelectionModel().getSelectedItem())) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (movies.get(i).getName().equals(name.getText()) // заглавие + жанр - не работи
                                && isActorsEmpty
                                && movies.get(i).getGenre().equals(genre.getText())
                                && isYearEmpty) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (movies.get(i).getName().equals(name.getText()) // заглавие + година
                                && isActorsEmpty
                                && isGenreEmpty
                                && movies.get(i).getYear() == Integer.parseInt(year.getSelectionModel().getSelectedItem())) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (movies.get(i).getName().equals(name.getText()) // заглавие + актьори + жанр - не работи
                                && movies.get(i).getActors().contains(actors.getText())
                                && movies.get(i).getGenre().equals(genre.getText())
                                && isYearEmpty) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (movies.get(i).getName().equals(name.getText()) // заглавие + актьори + година
                                && movies.get(i).getActors().contains(actors.getText())
                                && isGenreEmpty
                                && movies.get(i).getYear() == Integer.parseInt(year.getSelectionModel().getSelectedItem())) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (movies.get(i).getName().equals(name.getText()) // заглавие + жанр + година
                                && isActorsEmpty
                                && movies.get(i).getGenre().equals(genre.getText())
                                && movies.get(i).getYear() == Integer.parseInt(year.getSelectionModel().getSelectedItem())) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (isNameEmpty // актьори + жанр
                                && movies.get(i).getActors().contains(actors.getText())
                                && movies.get(i).getGenre().equals(genre.getText())
                                && isYearEmpty) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (isNameEmpty // актьори + година
                                && movies.get(i).getActors().contains(actors.getText())
                                && isGenreEmpty
                                && movies.get(i).getYear() == Integer.parseInt(year.getSelectionModel().getSelectedItem())) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (isNameEmpty // актьори + жанр + година
                                && movies.get(i).getActors().contains(actors.getText())
                                && movies.get(i).getGenre().equals(genre.getText())
                                && movies.get(i).getYear() == Integer.parseInt(year.getSelectionModel().getSelectedItem())) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        } else if (isNameEmpty // жанр + година
                                && isActorsEmpty
                                && movies.get(i).getGenre().equals(genre.getText())
                                && movies.get(i).getYear() == Integer.parseInt(year.getSelectionModel().getSelectedItem())) {
                            res += movies.get(i).getName() + "\n"
                                    + movies.get(i).getActors() + "\n"
                                    + movies.get(i).getGenre() + "\n"
                                    + movies.get(i).getYear() + "\n\n";
                        }
                    }
                    textArea.setText(res);
                } catch (NumberFormatException e) {
                    MessageBox.show("Грешка", "Празен combobox.");
                }
            }
        });
        Button cancelButton = new Button("Отказ");
        cancelButton.setOnAction(event -> window.setScene(scene1));

        HBox hBox1 = new HBox();
        hBox1.setPadding(new Insets(10, 10, 10, 10));
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(namelabel, name);
        hBox1.setAlignment(Pos.BASELINE_RIGHT);

        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(10, 10, 10, 10));
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(actorslabel, actors);
        hBox2.setAlignment(Pos.BASELINE_RIGHT);

        HBox hBox3 = new HBox();
        hBox3.setPadding(new Insets(10, 10, 10, 10));
        hBox3.setSpacing(10);
        hBox3.getChildren().addAll(genrelabel, genre);
        hBox3.setAlignment(Pos.BASELINE_RIGHT);

        HBox hBox4 = new HBox();
        hBox4.setPadding(new Insets(10, 10, 10, 10));
        hBox4.setSpacing(10);
        hBox4.getChildren().addAll(yearlabel, year);
        hBox4.setAlignment(Pos.CENTER);

        HBox hBox5 = new HBox();
        hBox5.setPadding(new Insets(10, 10, 10, 10));
        hBox5.setSpacing(10);
        hBox5.getChildren().addAll(button, cancelButton);
        hBox5.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5);


        HBox HBoxScene = new HBox();
        HBoxScene.setPadding(new Insets(10, 10, 10, 10));
        HBoxScene.setSpacing(10);
        HBoxScene.getChildren().addAll(textArea, vBox);
        HBoxScene.setAlignment(Pos.CENTER);
        HBoxScene.setBackground(new Background(new BackgroundFill(Color.rgb(0, 150, 200), CornerRadii.EMPTY, Insets.EMPTY)));


        scene2 = new Scene(HBoxScene, 550, 300);
        return scene2;
    }

    private Scene AddMovie() {
        Label label = new Label("Добавете филм");
        Label namelabel = new Label("Име: ");
        Label actorslabel = new Label("Актьори: ");
        Label genrelabel = new Label("Жанр: ");
        Label yearlabel = new Label("Година: ");

        TextField name = new TextField();
        TextField actors = new TextField();
        ComboBox<String> genre = new ComboBox<String>();
        for(int i=0; i< genres.length;i++){
            genre.getItems().add(genres[i]);
        }
        ComboBox<String> year = new ComboBox<String>();
        for(int i=2021;i>=1900;i--){
            year.getItems().add(Integer.toString(i));
        }

        Button addButton = new Button("Добавете филм");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!name.getText().equals("") &&
                        !actors.getText().equals("") &&
                        !genre.getSelectionModel().isEmpty() &&
                        !year.getSelectionModel().isEmpty()) {

                    dataBase.add(new Movie(name.getText(), actors.getText(), genre.getSelectionModel().getSelectedItem().toString(),
                            Integer.parseInt(year.getSelectionModel().getSelectedItem())));
                    MessageBox.show("Успешно!", "Добавихте нов филм!");
                } else
                    MessageBox.show("Грешка!", "Моля попълнете всички полета.");
            }
        });
        Button cancelButton = new Button("Отказ");
        cancelButton.setOnAction(event -> window.setScene(scene1));


        HBox hBox1 = new HBox();
        hBox1.setPadding(new Insets(10, 10, 10, 10));
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(namelabel, name);
        hBox1.setAlignment(Pos.BASELINE_RIGHT);

        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(10, 10, 10, 10));
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(actorslabel, actors);
        hBox2.setAlignment(Pos.BASELINE_RIGHT);

        HBox hBox3 = new HBox();
        hBox3.setPadding(new Insets(10, 10, 10, 10));
        hBox3.setSpacing(10);
        hBox3.getChildren().addAll(genrelabel, genre);
        hBox3.setAlignment(Pos.BASELINE_RIGHT);

        HBox hBox4 = new HBox();
        hBox4.setPadding(new Insets(10, 10, 10, 10));
        hBox4.setSpacing(10);
        hBox4.getChildren().addAll(yearlabel, year);
        hBox4.setAlignment(Pos.BASELINE_RIGHT);

        HBox hBox5 = new HBox();
        hBox5.setPadding(new Insets(10, 10, 10, 10));
        hBox5.setSpacing(10);
        hBox5.getChildren().addAll(addButton, cancelButton);
        hBox5.setAlignment(Pos.BASELINE_RIGHT);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5);
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 150, 200), CornerRadii.EMPTY, Insets.EMPTY)));
        scene2 = new Scene(vBox, 350, 320);

        return scene2;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
