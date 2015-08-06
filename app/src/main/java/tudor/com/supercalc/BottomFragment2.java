package tudor.com.supercalc;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BottomFragment2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BottomFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomFragment2 extends Fragment {
	private Button mButtonLogarithm;
	private Button mButtonLogarithmNat;

	private Button mButtonPi;
	private Button mButtonE;


	private OnFragmentInteractionListener mListener;

	public static BottomFragment2 newInstance() {
		BottomFragment2 fragment = new BottomFragment2();
		return fragment;
	}

	public BottomFragment2() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_bottom_fragment2, container, false);

//		mButtonLogarithm = (Button) view.findViewById(R.id.buttonLogarithm);
//		mButtonLogarithmNat = (Button) view.findViewById(R.id.buttonLogarithmNat);
//
//		mButtonPi = (Button) view.findViewById(R.id.buttonPi);
//		mButtonE = (Button) view.findViewById(R.id.buttonE);


		return view;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

}