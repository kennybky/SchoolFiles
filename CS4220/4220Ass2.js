//Quesiton 1
class  Groups {
	constructor(groups = []) {
		this.groups = groups

		
	}

	addGroup(group) {
		let g = new Object();
		g.name= group.name
		g.leader = group.leader
		g.members = group.members
		this.groups.push(g)

	}

	removeGroup(groupName) {
		let result = this.groups.filter(group => group.name.toLowerCase() != groupName.toLowerCase())
		this.groups = result;
	}

	addMember(groupName, memberName) {

		let element = this.groups.find(group => {
 			 return group.name.toLowerCase() == groupName.toLowerCase();
		});

		element.members.push(memberName);
	}

	removeMember(groupName, memberName) {
		let element = this.groups.find(group => {
 			 return group.name.toLowerCase() == groupName.toLowerCase();
		});

		let members = element.members.filter(member => member.toLowerCase() != memberName.toLowerCase())
		element.members = members
	}

	get print() {
		this.groups.forEach(group=>{
			console.log(group.name)
			console.log("Leader: " + group.leader)
			console.log(group.members.join(' | '))
			console.log("\n");
		})
	}

}

//Question2
function displayName({first, last} = person) {
	let name = `${first} ${last}`
	console.log(name)

}


//Question3
function combineName(person, keys, dest) {
	let newVal = keys.map(key=> person[key])
	person[dest] = newVal.join(" ")
	keys.forEach(key =>{
		delete person[key]
	})
}


//Question4
function createObject(people) {
	let persons = new Object()
	let i = 1;
	people.forEach(person=> {
		let obj = new Object()
		person.forEach(attr=>{
			key = attr['key']
			value = attr['value']
			obj[key] = value
		})
		persons[i] = obj
		i++
	})

	return persons
}