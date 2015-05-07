package models

import reactivemongo.bson.BSONObjectID
import java.util.Date

case class Employee(
  _id: BSONObjectID,
  date: Date,
  name: String,
  address: String,
  dob: Date,
  joiningDate: Date,
  designation: String
)

case class User(
  _id: BSONObjectID,
  username: String,
  passwordDigest: String
)

case class Stat(
  _id: BSONObjectID,
  userId: BSONObjectID,
  name: String,
  value: String
)

case class WorkoutPlan(
  _id: BSONObjectID,
  name: String,
  exercises: Seq[String]
)

case class Workout(
  _id: BSONObjectID,
  userId: BSONObjectID,
  workoutPlanId: BSONObjectID,
  weight: Float,
  exerciseToWeightLifted: Map[String, Seq[Int]]
)

/**
 * Helper for pagination.
 */
case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}

object JsonFormats {
  import play.api.libs.json.Json
  import play.api.data._
  import play.api.data.Forms._
  import play.modules.reactivemongo.json.BSONFormats._

  // Generates Writes and Reads for Feed and User thanks to Json Macros
  implicit val employeeFormat = Json.format[Employee]
}