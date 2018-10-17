package edu.gwu.trivia.model.generated.petfinder


import com.squareup.moshi.Json


data class PetItem(

	@field:Json(name="sex")
	val sex: Sex? = null,

	@field:Json(name="description")
	val description: Description? = null,

	@field:Json(name="media")
	val media: Media? = null,

	@field:Json(name="shelterId")
	val shelterId: ShelterId? = null,

//	@field:Json(name="breeds") //removing breed because API is stupid - sometimes breed comes back as an object, sometimes it comes back as an array of objects - this messes up our Moshi JSON parsing :(
//	val breeds: Breeds? = null,

	@field:Json(name="size")
	val size: Size? = null,

	@field:Json(name="contact")
	val contact: Contact? = null,

	@field:Json(name="lastUpdate")
	val lastUpdate: LastUpdate? = null,

	@field:Json(name="options")
	val options: Options? = null,

	@field:Json(name="name")
	val name: Name? = null,

	@field:Json(name="animal")
	val animal: Animal? = null,

	@field:Json(name="id")
	val id: Id? = null,

	@field:Json(name="mix")
	val mix: Mix? = null,

	@field:Json(name="age")
	val age: Age? = null,

	@field:Json(name="shelterPetId")
	val shelterPetId: ShelterPetId? = null,

	@field:Json(name="status")
	val status: Status? = null
)