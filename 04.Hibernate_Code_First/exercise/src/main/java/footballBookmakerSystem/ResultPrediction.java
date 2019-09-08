package footballBookmakerSystem;

import core.BaseEntity;

import java.io.Serializable;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends BaseEntity implements Serializable {

  private Prediction prediction;

  public ResultPrediction() {
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "prediction")
  public Prediction getPrediction() {
    return prediction;
  }

  public void setPrediction(Prediction prediction) {
    this.prediction = prediction;
  }
}
